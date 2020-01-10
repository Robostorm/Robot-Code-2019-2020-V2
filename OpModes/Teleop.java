package org.firstinspires.ftc.teamcode.OpModes;

import android.renderscript.ScriptIntrinsicYuvToRGB;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Robot.Drive;
import org.firstinspires.ftc.teamcode.Robot.Robot;

/**
 * TeleOp class, contains separate functions that run the robot during driver operated period of the game
 * @author Aidan Ferry
 * @since 9/8/2019
 */

@TeleOp(name="Teleop")
public class Teleop extends OpMode
{
    public static float minspeed = 0.5f; // with gas pedal not pressed
    public static float maxspeed = 1.5f; // with gas pedal at full

    public static final int liftClearanceHeight = 1300;

    private static boolean timerstart=false;
    private static long ytimeout = 0;
    private static long btimeout = 0;
    private static long throwbackTime = 0;
    private static long throwdownTime = 0;
    private static long curtime;
    private static boolean inverted=false;
    private static long rbumptimer=0;
    private static long lbumptimer=0;
    private static long dpaddowntimer = 0;
    private static long dpaduptimer = 0;
    private static boolean dpadleftstart=false;
    private static boolean dpadrightstart=false;
    private static long dpadlefthold =0;
    private static long dpadrighthold = 0;
    private static long intakeArmMoving=0;
    private static long grabTimer = 0;
    private static boolean switching=false;
    private static boolean fromheld=false;
    private static boolean grabbing=false;

    private static int[] intakeArmPositions = {0,1500,1950};
    private static int intakeArmPos=0;

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    Robot robot = new Robot();

    //construct drive class
    Drive drive = new Drive(robot);

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initializing");

        robot.init(hardwareMap);

        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        float mult = gamepad1.right_trigger;
        float rx = gamepad1.right_stick_x;
        float ry = gamepad1.right_stick_y;
        float lx = gamepad1.left_stick_x;
        float ly = gamepad1.left_stick_y;
        mult = map(mult, 0,1,minspeed,maxspeed);
        drive.setMotorPower((rx)*mult, -(ry)*mult, (lx)*Math.abs(mult), -(ly)*mult, true);//if you change doFunction, make sure to also change it in RRBotAutoReader
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

    public static float map(float x, float in_min, float in_max, float out_min, float out_max)
    {
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }
}
