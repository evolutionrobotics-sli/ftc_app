package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.lang.reflect.Modifier;


@TeleOp(name="Final POV Mode for 2nd comp", group="Competition")  // @Autonomous(...) is the other common choice
public class AWDTeleOpPOVMode extends LinearOpMode {

    /** Evolution Robotics
     * POV Teleop w/ speed switches
     */
    //POV TELEOP
    //!!!!!!!!!!
    //hi its gaurav
    //this is my pov teleop
    //ill comment whatever need be
    //if you need explanation ask me
    //thanks and byeee

    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor motorFL, motorBL, motorFR, motorBR;
    double modifierVal = 0.75;
    boolean runFast, runSlow;
    double left, left2;
    double right, right2;
    double max;
    double slowSpeed = 0.25;
    double fastSpeed = 1;
    double restingSpeed = 0.75;
    //code for the shovels
    //boolean switchSides;

    @Override
    public void runOpMode() {
        //registering motors
        //change what is in quotes to what you have in your own robot configuration

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        motorFL  = hardwareMap.dcMotor.get("fl_drive");
        motorBL  = hardwareMap.dcMotor.get("bl_drive");
        motorFR  = hardwareMap.dcMotor.get("fr_drive");
        motorBR  = hardwareMap.dcMotor.get("br_drive");

        // "Reverse" the motor that runs backwards when connected directly to the battery
        motorFL.setDirection(DcMotor.Direction.FORWARD);
        motorBL.setDirection(DcMotor.Direction.FORWARD);
        motorFR.setDirection(DcMotor.Direction.REVERSE);
        motorBR.setDirection(DcMotor.Direction.REVERSE);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {



            //these 3 are all switches
            //they control speed
            //if you want to change the speed, change the variable in the declaration of opmode members
            /*
            psuedocode
            if(button) {
            boolean = opposite of boolean
            (this means if
            false -> true
            true -> false)
            }
             */
            //change controls if you want

            //boolean switches for fast/slow
            //fast
            if(gamepad1.right_bumper) {
                runFast = !runFast;
            }

            //slow
            if(gamepad1.left_bumper) {
                runSlow = !runSlow;
            }

            //changing speed + resetting the switch, etc.
            //running fast
            if(runFast) {
                if(modifierVal == restingSpeed) {
                    modifierVal = fastSpeed;
                }
                else {
                    modifierVal = restingSpeed;
                    runSlow = false;
                }
                runFast = !runFast;
            }

            //running slow
            if(runSlow) {
                if(modifierVal == restingSpeed) {
                    modifierVal = slowSpeed;
                }
                else{
                    modifierVal = restingSpeed;
                    runFast = false;
                }
                runSlow = !runSlow;
            }

            //calculates the final value going to the motors
            left  = -gamepad1.left_stick_y + gamepad1.right_stick_x;
            right = -gamepad1.left_stick_y - gamepad1.right_stick_x;
            left2 = left * modifierVal;
            right2 = right * modifierVal;

            // Normalize the values so neither exceed +/- 1.0
            max = Math.max(Math.abs(left2), Math.abs(right2));
            if (max > 1.0)
            {
                left2 /= max;
                right2 /= max;
            }

            //adds a telemetry message telling what speed you are going at
            telemetry.addData("Say", "Running at " + modifierVal + " times speed");
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();

            /* code for the shovels
            if(gamepad1.dpad_up) {
                switchSides = !switchSides;
            }
            if(switchSides){
                left2 = -left2;
                right2 = -right2;
            }
            */



            //setting motor powers
            motorFL.setPower(left2);
            motorBL.setPower(left2);
            motorFR.setPower(right2);
            motorBR.setPower(right2);
        }
    }
}
