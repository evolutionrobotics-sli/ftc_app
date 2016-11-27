package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name="MecanumWheelDrive", group="Testing")  // @Autonomous(...) is the other common choice
public class MecanumWheelDrive extends LinearOpMode {

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
    private double modifierVal = 0.75;
    private boolean runFast, runSlow;
    private double powFL, powBL, powFR, powBR;
    private double powFL2, powBL2, powFR2, powBR2;
    private double powFL3, powBL3, powFR3, powBR3;
    private double deadzone = 0.1;
    private double ch1, ch3, ch4;
    private double slowSpeed = 0.25;
    private double fastSpeed = 1;
    private double restingSpeed = 0.75;
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
                runSlow = false;
                if(modifierVal == restingSpeed) {
                    modifierVal = fastSpeed;
                }
                else {
                    modifierVal = restingSpeed;
                }
                runFast = !runFast;
            }


            //running slow
            if(runSlow) {
                runFast = false;
                if(modifierVal == restingSpeed) {
                    modifierVal = slowSpeed;
                }
                else{
                    modifierVal = restingSpeed;
                }
                runSlow = !runSlow;
            }

            ch1 = gamepad1.right_stick_x;
            ch3 = -gamepad1.left_stick_y;
            ch4 = gamepad1.left_stick_x;

            //FL = 1
            powFL = ch3 + ch1 + ch4;
            //BL = 3
            powBL = ch3 + ch1 - ch4;
            //FR = 2
            powFR = ch3 - ch1 - ch4;
            //BR = 4
            powBR = ch3 - ch1 + ch4;

            if(Math.abs(powFL) > deadzone) {
                powFL2 = powFL;
            }
            else {
                powFL2 = 0;
            }

            if(Math.abs(powBL) > deadzone) {
                powBL2 = powBL;
            }
            else {
                powBL2 = 0;
            }

            if(Math.abs(powFR) > deadzone) {
                powFR2 = powFR;
            }
            else {
                powFR2 = 0;
            }

            if(Math.abs(powBR) > deadzone) {
                powBR2 = powBR;
            }
            else {
                powBR2 = 0;
            }


            powFL3 = powFL2 * modifierVal;
            powBL3 = powBL2 * modifierVal;
            powFR3 = powFR2 * modifierVal;
            powBR3 = powBR2 * modifierVal;



            telemetry.addData("Running at", modifierVal + "times speed");
            telemetry.addData("Time until match ends", 230 - runtime.seconds());






            //setting motor powers
            motorFL.setPower(powFL3);
            motorBL.setPower(powBL3);
            motorFR.setPower(powFR3);
            motorBR.setPower(powBR3);
        }
    }
}
