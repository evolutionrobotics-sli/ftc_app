
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name="4WD Pov Mode - competition", group="Test OpModes")  // @Autonomous(...) is the other common choice]
@Disabled
public class AWDTeleOpJoystickTest extends LinearOpMode {

//sorry for the primitive code whoever is using this
    //-g


    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor motorFL, motorBL, motorFR, motorBR;
    double max = 0;
    double modifierVal = 1;
    boolean runSlow = false;
    boolean runFast = false;
    boolean overrideSpeed = false;








    @Override
    public void runOpMode() {


        telemetry.addData("Status", "Initialized");
        telemetry.update();



        motorFL  = hardwareMap.dcMotor.get("fl_drive");
        motorBL  = hardwareMap.dcMotor.get("bl_drive");
        motorFR  = hardwareMap.dcMotor.get("fr_drive");
        motorBR  = hardwareMap.dcMotor.get("br_drive");
        double leftPower, rightPower;

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
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();

            leftPower = -gamepad1.left_stick_y + gamepad1.right_stick_x;
            rightPower = +gamepad1.left_stick_y -gamepad1.right_stick_x;
            max = Math.max(Math.abs(leftPower), Math.abs(rightPower));
            if (max > 1.0) {
                leftPower /= max;
                rightPower /= max;
            }
            double left = leftPower * modifierVal;
            double right = rightPower * modifierVal;

            if(gamepad1.left_bumper) {
                runSlow = !runSlow;
            }

            if(gamepad1.right_bumper){
                runFast = !runFast;
            }

            if(gamepad1.dpad_up){
                overrideSpeed = !overrideSpeed;

            }


            if(overrideSpeed){
                modifierVal = 1;
            }


            if(runSlow){
                if(modifierVal == 0.25) {
                    modifierVal = 1;
                }
                else if (modifierVal == 1) {
                    modifierVal = 0.25;
                }
                else {}
                telemetry.addData("Say", "Running in slow mode");
                telemetry.update();
            }
            else{}


            if(runFast) {
                if(modifierVal == 0.75) {
                    modifierVal = 1;
                }
                else if(modifierVal == 1) {
                    modifierVal = 0.75;
                }
                else{}
                telemetry.addData("Say", "Running in fast mode");
                telemetry.update();
            }




            motorFL.setPower(left);
            motorBL.setPower(left);
            motorFR.setPower(right);
            motorBR.setPower(right);







            idle();
        }
    }
}
