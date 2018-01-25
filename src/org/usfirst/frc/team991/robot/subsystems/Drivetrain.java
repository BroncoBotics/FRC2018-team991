package org.usfirst.frc.team991.robot.subsystems;

import java.text.DecimalFormat;

import org.usfirst.frc.team991.robot.Robot;
import org.usfirst.frc.team991.robot.RobotMap;
import org.usfirst.frc.team991.robot.commands.arcadeDriveWithJoystick;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drivetrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	TalonSRX leftFront = new TalonSRX(RobotMap.leftFront);
	TalonSRX rightFront = new TalonSRX(RobotMap.rightFront);
	TalonSRX leftBack = new TalonSRX(RobotMap.leftBack);
	TalonSRX rightBack = new TalonSRX(RobotMap.rightBack);
	
	

	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new arcadeDriveWithJoystick());
    }
    
    public void tankDrive(double leftSpeed, double rightSpeed) {
    	leftFront.set(ControlMode.PercentOutput,leftSpeed);
    	rightFront.set(ControlMode.PercentOutput,rightSpeed);
    	leftBack.set(ControlMode.PercentOutput,-leftSpeed);
    	rightBack.set(ControlMode.PercentOutput,rightSpeed);
    }
    
    public void arcadeDrive(double moveValue, double rotateValue, boolean squaredInputs) {
        double leftMotorSpeed, rightMotorSpeed;
        moveValue = -moveValue;

        //moveValue = limit(moveValue);
        //rotateValue = limit(rotateValue);

        if (squaredInputs) {
            // square the inputs (while preserving the sign) to increase fine control while permitting full power
            if (moveValue >= 0.0) {
                moveValue = (moveValue * moveValue);
            } else {
                moveValue = -(moveValue * moveValue);
            }
            if (rotateValue >= 0.0) {
                rotateValue = (rotateValue * rotateValue);
            } else {
                rotateValue = -(rotateValue * rotateValue);
            }
        }

        if (moveValue > 0.0) {
            if (rotateValue > 0.0) {
                leftMotorSpeed = moveValue - rotateValue;
                rightMotorSpeed = Math.max(moveValue, rotateValue);
            } else {
                leftMotorSpeed = Math.max(moveValue, -rotateValue);
                rightMotorSpeed = moveValue + rotateValue;
            }
        } else {
            if (rotateValue > 0.0) {
                leftMotorSpeed = -Math.max(-moveValue, rotateValue);
                rightMotorSpeed = moveValue + rotateValue;
            } else {
                leftMotorSpeed = moveValue - rotateValue;
                rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
            }
        }

        leftFront.set(ControlMode.PercentOutput,leftMotorSpeed);
    	rightFront.set(ControlMode.PercentOutput,rightMotorSpeed);
    	leftBack.set(ControlMode.PercentOutput,-leftMotorSpeed);
    	rightBack.set(ControlMode.PercentOutput,rightMotorSpeed);

    	
    	DecimalFormat df = new DecimalFormat("#.##");
    	
    	SmartDashboard.putString("Left Motor Speed", df.format(leftMotorSpeed));
    	SmartDashboard.putString("Right Motor Speed", df.format(rightMotorSpeed));
    	
    	SmartDashboard.putString("LF", df.format(leftMotorSpeed));
    	SmartDashboard.putString("RF", df.format(rightMotorSpeed));
    	SmartDashboard.putString("LB", df.format(leftMotorSpeed));
    	SmartDashboard.putString("RB", df.format(rightMotorSpeed));
    	
    	SmartDashboard.putNumber("LF Raw", leftFront.getMotorOutputPercent());
    	SmartDashboard.putNumber("RF Raw", rightFront.getMotorOutputPercent());
    	SmartDashboard.putNumber("LB Raw", leftBack.getMotorOutputPercent());
    	SmartDashboard.putNumber("RB Raw", rightBack.getMotorOutputPercent());
    	
    }


    public void stop() {
    	leftFront.set(ControlMode.PercentOutput,0.0);
    	rightFront.set(ControlMode.PercentOutput,0.0);
    	leftBack.set(ControlMode.PercentOutput,0.0);
    	rightBack.set(ControlMode.PercentOutput,0.0);

    }
}

