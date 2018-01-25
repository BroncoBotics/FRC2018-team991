package org.usfirst.frc.team991.robot.subsystems;

import org.usfirst.frc.team991.robot.RobotMap;
import org.usfirst.frc.team991.robot.commands.makeSuckerSuck;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Sucker extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	TalonSRX leftFront = new TalonSRX(RobotMap.leftSucker);
	TalonSRX rightFront = new TalonSRX(RobotMap.rightSucker);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new makeSuckerSuck());
    }
    
    public void suckMove(double power, double power2, boolean squaredInputs) {
    	if(squaredInputs)
    	{
    		leftFront.set(ControlMode.PercentOutput,power*power);
    		rightFront.set(ControlMode.PercentOutput,-power2*power2);
    	}else {
    		leftFront.set(ControlMode.PercentOutput,power);
    		rightFront.set(ControlMode.PercentOutput,-power2);
    	}
    	
    }
}

