package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Gaurav on 11/27/2016.
 */

public class RobotHardware {
    public DcMotor motorFL = null;
    public DcMotor motorBL = null;
    public DcMotor motorFR = null;
    public DcMotor motorBR = null;
    public Servo servoAxle = null;
    public ColorSensor sensorColor;

    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();


    public RobotHardware() {}

    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;

        motorFL = hwMap.dcMotor.get("fl_drive");
        motorBL = hwMap.dcMotor.get("bl_drive");
        motorFR = hwMap.dcMotor.get("fr_drive");
        motorBR = hwMap.dcMotor.get("br_drive");
        servoAxle = hwMap.servo.get("axle_servo");
        sensorColor = hwMap.colorSensor.get("color_sensor");


        motorFL.setDirection(DcMotorSimple.Direction.FORWARD);
        motorBL.setDirection(DcMotorSimple.Direction.FORWARD);
        motorFR.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBR.setDirection(DcMotorSimple.Direction.REVERSE);

        motorFL.setPower(0);
        motorBL.setPower(0);
        motorFR.setPower(0);
        motorBR.setPower(0);
        servoAxle.setPosition(0);

        motorFL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorBL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorFR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorBR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


    }
    public void waitForTick(long periodMs) {

        long  remaining = periodMs - (long)period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0) {
            try {
                Thread.sleep(remaining);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Reset the cycle clock for the next pass.
        period.reset();
    }
}
