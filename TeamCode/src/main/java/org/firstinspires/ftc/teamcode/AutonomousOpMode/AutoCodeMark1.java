package org.firstinspires.ftc.teamcode.AutonomousOpMode;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import static com.pedropathing.api.Paths.curve;
import static com.pedropathing.api.Paths.line;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.commands.Commands.instant;
import static com.pedropathing.ivy.commands.Commands.waitUntil;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow ;

import org.firstinspires.ftc.teamcode.PositStorage;
import org.firstinspires.ftc.teamcode.pedro.Constants;


/**
 * WELCOME TO THE AUTONOMOUS EXERCISE!! o(╹ڡ╹ )
 * Much of this code is written for you! The parts that need completion are underlined in yellow. Dotted lines are optional.
 * Please reach out if there's any questions.
 * @Author m-miha on GitHub.
 * @Email mjm2442@colubmia.edu
 */

/**
 * For this exercise, you'll be writing an autonomous opmode using PedroPath and the Ivy command system.
 * The robot will, from its starting position, drive to a scoring position, turn on a motor to fire pollen, then move to its park position.
 *
 */

@Autonomous
public class AutoCodeMark1 extends OpMode {
    private Follower follower;
    private DcMotor flywheel;
    /**
     * This poseFactory object specifies that our poses will use degrees instead of radians.
     */
    private final PoseFactory poseFactory = PoseFactory.degrees();


    public static class Paths {

        private static final PoseFactory poseFactory = PoseFactory.degrees();

        private static final Pose start = poseFactory.of(48.0686, 6.3125, 90);
        private static final Pose path1 = poseFactory.of(48.7436, 22.1622, 80);
        private static final Pose point2Start = poseFactory.of(48.7436, 22.1622, 80);
        private static final Pose point2 = poseFactory.of(8.1797, 7.0125, 90);
        private static final Pose point2Control1 = poseFactory.of(30.1832, 59.3324, 0);
        private static final Pose point3Start = poseFactory.of(8.1797, 7.0125, 90);
        private static final Pose point3 = poseFactory.of(41.2986, 122.1168, -60);
        private static final Pose point4Start = poseFactory.of(41.2986, 122.1168, -50);
        private static final Pose point4 = poseFactory.of(57.1588, 9.7509, -270);
        private static final Pose point4Control1 = poseFactory.of(69.6228, 111.6617, 0);
        private static final Pose point5Start = poseFactory.of(57.1588, 9.7509, -270);
        private static final Pose point5 = poseFactory.of(7.5548, 97.6119, 0);
        private static final Pose point5Control1 = poseFactory.of(14.2369, 45.7069, 0);




    // Path methods
        public static Path path1() {
            return line(start, path1).linear(start, path1);
        }

            public static Path path2() {
                return curve(point2Start, point2Control1, point2).linear(point2Start, point2);
            }

            public static Path path3() {
                return line(point3Start, point3).linear(point3Start, point3);
            }

            public static Path path4() {
                return curve(point4Start, point4Control1, point4).linear(point4Start, point4);
            }

            public static Path path5() {
                return curve(point5Start, point5Control1, point5).linear(point5Start, point5);
            }
        }



    /**
     * Construct the following method to return a command for our autonomous routine. Commands string together path following with other arbitrary code you'd want executed in the middle of your
     * paths. In this case, we want to drive to our score pose, set the flywheel power to 1, wait 500ms, turn the flywheel off (power 0), then drive to our
     * park position.
     *
     * See Ivy's Utility Commands: https://pedropathing.com/docs/ivy/utilities-and-decorators
     */
    private Command autoShot() {
        return sequential(
                follow(follower, Paths.path1()),
                instant(() -> flywheel.setPower(1)),
                waitUntil(() -> flywheel.getCurrentPosition() > 1000),
                instant(() -> flywheel.setPower(0)),
                follow(follower, Paths.path5())
        );
    }

    @Override
    public void init() {
        Scheduler.reset();

        follower = Constants.create(hardwareMap);
        /**
         * Instantiate the flywheel object with configuration name "flywheel"
         */
        flywheel = hardwareMap.get(DcMotor.class, "flywheel");
        follower.setPose(Paths.start);
        follower.update();
    }

    @Override
    public void start() {
        /**
         * Use the "schedule()" method to prepare our autonomous command.
         */
        schedule();
    }

    @Override
    public void loop() {
        follower.update();
        Scheduler.execute();

    }
    @Override
    public void stop() {
        PositStorage.autonomousEndPose = follower.pose(); //saves your position in that file
    }
}

