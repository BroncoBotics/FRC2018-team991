package org.usfirst.frc.team991.robot.subsystems;

import org.usfirst.frc.team991.robot.RobotMap;
import org.usfirst.frc.team991.robot.commands.makeSuckerSuck;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Arm extends Subsystem {
		
	TalonSRX actLeft = new TalonSRX(RobotMap.actuatorLeft);
	TalonSRX actRight = new TalonSRX(RobotMap.actuatorRight);
	
	
	
    public void initDefaultCommand() {
    	setDefaultCommand(new makeSuckerSuck());
    }
    
    public void moveArmPower(double power) {
    	actLeft.set(ControlMode.PercentOutput,power);
    	actRight.set(ControlMode.PercentOutput,-power);
    }

    public void stop() {
    	actLeft.set(ControlMode.PercentOutput,0.0);
    }
}

