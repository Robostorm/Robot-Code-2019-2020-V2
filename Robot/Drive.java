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
}
