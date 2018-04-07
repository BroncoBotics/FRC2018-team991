package org.usfirst.frc.team991.robot.subsystems;

import java.text.DecimalFormat;

import org.usfirst.frc.team991.robot.Robot;
import org.usfirst.frc.team991.robot.RobotMap;
import org.usfirst.frc.team991.robot.commands.arcadeDriveWithJoystick;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import com.analog.adis16448.frc.ADIS16448_IMU;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drivetrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	TalonSRX leftFront;
	TalonSRX rightFront;
	TalonSRX leftBack;
	TalonSRX rightBack;
	Potentiometer meter;
	
	private ADIS16448_IMU gyro;

	public Drivetrain() {
		leftFront = new TalonSRX(RobotMap.leftFront);
		rightFront = new TalonSRX(RobotMap.rightFront);
		leftBack = new TalonSRX(RobotMap.leftBack);
		rightBack = new TalonSRX(RobotMap.rightBack);
		
		
		AnalogInput ai = new AnalogInput(0);
		meter = new AnalogPotentiometer(ai, 99, 1);
		
		gyro = new ADIS16448_IMU();
	}
	
	public double getPotent() {
		return meter.get();
	}
	
	public void readUsage() {
		DecimalFormat df = new DecimalFormat("###.##");
		String lf = df.format(leftFront.getOutputCurrent()) + " / " + df.format(leftFront.getTemperature()) + " / " + df.format(leftFront.getBusVoltage()) + " / " + df.format(leftFront.getMotorOutputPercent()) + " / " + df.format(leftFront.getMotorOutputVoltage());
		String rf = df.format(rightFront.getOutputCurrent()) + " / " + df.format(rightFront.getTemperature()) + " / " + df.format(rightFront.getBusVoltage()) + " / " + df.format(rightFront.getMotorOutputPercent()) + " / " + df.format(rightFront.getMotorOutputVoltage());
		String lb = df.format(leftBack.getOutputCurrent()) + " / " + df.format(leftBack.getTemperature()) + " / " + df.format(leftBack.getBusVoltage()) + " / " + df.format(leftBack.getMotorOutputPercent()) + " / " + df.format(leftBack.getMotorOutputVoltage());
		String rb = df.format(rightBack.getOutputCurrent()) + " / " + df.format(rightBack.getTemperature()) + " / " + df.format(rightBack.getBusVoltage()) + " / " + df.format(rightBack.getMotorOutputPercent()) + " / " + df.format(rightBack.getMotorOutputVoltage());
		String newline = System.getProperty("line.separator");
		System.out.println("Output Current / Temperature / Bus Voltage / Output Percent / Output Voltage" + newline + lf + newline + rf + newline + lb + newline+ rb);
		
	}
	
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
    	DecimalFormat df2 = new DecimalFormat("##.##");
    /*	SmartDashboard.putString("Left Motor Speed", df.format(leftMotorSpeed));
    	SmartDashboard.putString("Right Motor Speed", df.format(rightMotorSpeed));
    	
    /*	SmartDashboard.putNumber("Gyro AngleX", gyro.getAngleX());
    	SmartDashboard.putNumber("Gyro AngleY", gyro.getAngleY());
    	SmartDashboard.putNumber("Gyro AngleZ", gyro.getAngleZ());*/
    	
    	
    }


    public void stop() {
    	leftFront.set(ControlMode.PercentOutput,0.0);
    	rightFront.set(ControlMode.PercentOutput,0.0);
    	leftBack.set(ControlMode.PercentOutput,0.0);
    	rightBack.set(ControlMode.PercentOutput,0.0);

    }

    public void resetGryo() {
		gyro.reset();
	}

	//Gets currect gyro angle
	public double getGyroAngle() {
		return gyro.getAngleX();
	}
	
	public void calibrateGyro() {
		gyro.calibrate();
		
	}
	public ADIS16448_IMU getGyro() {
		return gyro;
	}
}

