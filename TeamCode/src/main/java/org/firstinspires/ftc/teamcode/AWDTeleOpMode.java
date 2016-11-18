package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.lang.reflect.Modifier;


@TeleOp(name="AWDOpMode", group="TankOpMode")  // @Autonomous(...) is the other common choice
public class AWDTeleOpMode extends LinearOpMode {

    //hi its gaurav
    //this is my tank teleop
    //ill comment whatever need be
    //if you need explanation ask me
    //thanks and byeee

    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor motorFL, motorBL, motorFR, motorBR;
    double modifierVal = 1;
    boolean runFast, runSlow;
    double finalLeft;
    double finalRight;
    double slowSpeed = 0.25;
    double fastSpeed = 0.75;
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
            if(gamepad1.right_bumper) {
                runFast = !runFast;
            }

            if(gamepad1.left_bumper) {
                runSlow = !runSlow;
            }

            if(runFast) {
                if(modifierVal == 1) {
                    modifierVal = fastSpeed;
                }
                else{
                    modifierVal = 1;
                }
            }

            if(runSlow) {
                if(modifierVal == 1) {
                    modifierVal = slowSpeed;
                }
                else{
                    modifierVal = 1;
                }
            }

            //calculates the final value going to the motors
            finalLeft = -gamepad1.left_stick_y * modifierVal;
            finalRight = -gamepad1.right_stick_y * modifierVal;

            //adds a telemetry message telling what speed you are going at
            telemetry.addData("Say", "Running at " + modifierVal + " times speed");
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();

            /* code for the shovels
            if(gamepad1.dpad_up) {
                switchSides = !switchSides;
            }
            if(switchSides){
                finalLeft = -finalLeft;
                finalRight = -finalRight;
            }
            */





            // eg: Run wheels in tank mode (note: The joystick goes negative when pushed forwards)

            motorFL.setPower(finalLeft);
            motorBL.setPower(finalLeft);
            motorFR.setPower(finalRight);
            motorBR.setPower(finalRight);
        }
    }
}