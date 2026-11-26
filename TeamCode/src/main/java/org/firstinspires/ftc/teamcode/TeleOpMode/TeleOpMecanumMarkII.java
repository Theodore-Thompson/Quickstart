package org.firstinspires.ftc.teamcode.TeleOpMode;

import com.pedropathing.drivetrain.DrivePowers;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.ManualDrive;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.PositStorage;
import org.firstinspires.ftc.teamcode.pedro.Constants;

@TeleOp(name = "Mark II")
public class TeleOpMecanumMarkII extends OpMode {

    private Follower follower;

    DcMotor transfer;
    DcMotor intake;
    DcMotor shooter;

    boolean checkingIntake = true;



    private DrivePowers powers;

    @Override
    public void init() {
        //follower = Constants.create(hardwareMap);
        transfer = hardwareMap.get(DcMotor.class, "transfer");
        intake = hardwareMap.get(DcMotor.class, "intake");
        shooter = hardwareMap.get(DcMotor.class, "shooter");

    }



    @Override
    public void loop() {
       // ManualDrive.driveOrHold(
        //                    follower,
         //                 -gamepad1.left_stick_y,
          //                 gamepad1.left_stick_x,
           //                gamepad1.right_stick_x
        //);
        //follower.manual(powers);
        // relocalise button
        //if (gamepad1.startWasPressed()) {
        //    Pose cornerPose = new Pose(10.5, 10.5, Math.toRadians(90));
            // On the fly Pose creation, we dont recommend this for Autonomous. Only accepts radians for heading
        //    follower.setPose(cornerPose); // overrides our pose
        //}

        if ((gamepad1.xWasPressed())){
            intake.setPower(-1);
            checkingIntake = true;
        }
        else if (gamepad1.bWasPressed()){
            intake.setPower(0.0);
            checkingIntake = false;
        }
        if ((gamepad1.aWasPressed())){
            transfer.setPower(1.0);
        }
        else if (gamepad1.yWasPressed()){
            intake.setPower(0.0);
        }
        shooter.setPower(gamepad1.right_trigger);


        //ManualDrive.driveOrHold(
        //            follower,
        //            -gamepad1.left_stick_y,
        //            gamepad1.left_stick_x,
        //            gamepad1.right_stick_x
        //    );
        /* This here is for this for a holding in order for the robot to not be pushed around when the


    //@Override
        //public void loop() {
        //    DrivePowers powers = ManualDrive.fieldCentric(
        //            -gamepad1.left_stick_y,
        //            gamepad1.left_stick_x,
        //            gamepad1.right_stick_x,
        //            follower.pose().heading()
        //    );
        //
        //    follower.manual(powers);
        //    follower.update();
        //}

        /* If it preferred by the driver for the motions to be relative to the way that he is facing; the code has been implemented here */
        //follower.manual(powers);
        //follower.update();
        //Pose robotPose = follower.pose(); // returns a Pose object
        //telemetry.addData("Robot X", robotPose.x());
        //telemetry.addData("Robot Y", robotPose.y());
        //telemetry.addData("Robot Heading", Math.toDegrees(robotPose.heading()));
        telemetry.addData("aww shit here we go again", "Intialized");
        //follower.update();
    }
    @Override
    public void start(){

        //follower.setPose(PositStorage.autonomousEndPose);
        //follower.update();

    }

}