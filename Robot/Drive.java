package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Utilities.Functions;

/**
 * Controls the robot's mecanum drive base
 * @author Aidan Ferry
 * @since December 2019
 */
public class Drive {
    Robot robot;
    public Drive(Robot robot)
    {
        this.robot = robot;
    }
    //Angle is parsed as follows: 0 is directly straight, 0 to 180 goes from straight forwards to straight backwards counter-clockwise (ex. 90 is directly left), 0 to -180 goes from straight forward to straight backwards clockwise (ex. -90 is directly right)
    public void directDrive(double angle, double inches, double speed){//POTENTIAL: add maxAcceleration
        angle+=90;
        if(angle<0) angle+=360;
        double FRBLpower = Math.sin(Math.toRadians(angle)-(Math.PI/4));
        double FLBRpower = Math.sin(Math.toRadians(angle)+(Math.PI/4));
    }
    public double inputFunction(double input)
    {
        double value = input * input;

        if(input < 0)
        {
            return value * -1;
        }
        else
        {
            return value;
        }
    }
    public double[] calcVelocities(double leftX, double leftY, double rightX, double rightY, boolean doFunction)
    {
        double moveX = rightX;
        double moveY1 = leftY;
        double turn = leftX;
        double moveY2 = rightY;

        //remap input values using a function
        if(doFunction)
        {
            moveX = inputFunction(moveX);
            moveY1 = inputFunction(moveY1);
            turn = inputFunction(turn);
            moveY2 = inputFunction(moveY2);
        }

        double v1 = moveY1 + moveX + turn + moveY2;
        double v2 = moveY1 - moveX - turn + moveY2;
        double v3 = moveY1 + moveX - turn + moveY2;
        double v4 = moveY1 - moveX + turn + moveY2;

        double max = Math.abs(v1);
        if(Math.abs(v2) > max)
            max = Math.abs(v2);
        if(Math.abs(v3) > max)
            max = Math.abs(v3);
        if(Math.abs(v4) > max)
            max = Math.abs(v4);
        if(max > 1)
        {
            v1 /= max;
            v2 /= max;
            v3 /= max;
            v4 /= max;
        }

        double[] velocities = {v1, v2, v3, v4};
        return velocities;
    }
    public void setMotorPower(double leftX, double leftY, double rightX, double rightY, boolean doFunction)
    {
        //calculate the velocities
        double[] velocities = calcVelocities(leftX, leftY, rightX, rightY, doFunction);

        //set the motor power
        robot.frontLeftMotor.setPower(velocities[0]);
        robot.frontRightMotor.setPower(velocities[1]);
        robot.rearLeftMotor.setPower(velocities[2]);
        robot.rearRightMotor.setPower(velocities[3]);
    }
}
