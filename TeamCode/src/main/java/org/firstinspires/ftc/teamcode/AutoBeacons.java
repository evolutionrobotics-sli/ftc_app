import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.robot.Robot;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RobotHardware;

@TeleOp(name="pla", group="pla")  // @Autonomous(...) is the other common choice
@Disabled
public class AutoBeacons extends LinearOpMode {

    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();
    // DcMotor leftMotor = null;
    // DcMotor rightMotor = null;
    double forwardSpeed = 0.2;
    double turnSpeed = 0.1;
    double timeCenter = 2.2;
    double timeFor90 = 1.0;
    double timeDriveBeacon = timeCenter;

    RobotHardware robot = new RobotHardware();

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        robot.init(hardwareMap);

        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)

        robot.motorFL.setPower(forwardSpeed);
        robot.motorBL.setPower(forwardSpeed);
        robot.motorFR.setPower(forwardSpeed);
        robot.motorBR.setPower(forwardSpeed);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < timeCenter)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        robot.motorFL.setPower(turnSpeed);
        robot.motorBL.setPower(turnSpeed);
        robot.motorFR.setPower(-turnSpeed);
        robot.motorBR.setPower(-turnSpeed);
        while (opModeIsActive() && (runtime.seconds() < timeFor90)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        robot.motorFL.setPower(forwardSpeed);
        robot.motorBL.setPower(forwardSpeed);
        robot.motorFR.setPower(forwardSpeed);
        robot.motorBR.setPower(forwardSpeed);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < timeDriveBeacon)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        robot.sensorColor.getI2cAddress();


    }
}