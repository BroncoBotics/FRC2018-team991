package org.usfirst.frc.team991.robot.subsystems;

import org.usfirst.frc.team991.robot.RobotMap;
import org.usfirst.frc.team991.robot.commands.runArmWithStick;
import org.usfirst.frc.team991.robot.commands.runClimber;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

	
	TalonSRX actuator = new TalonSRX(4);
	
	
	
	
    public void initDefaultCommand() {
    	setDefaultCommand(new runClimber());
    }
    
    public void moveClimber(double power) {
    	if(Math.abs(power) > 0.05) {
    		actuator.set(ControlMode.PercentOutput,-power);
    	} else {
    		actuator.set(ControlMode.PercentOutput,0.0);
    	}
    }

    public void stop() {
    	actuator.set(ControlMode.PercentOutput,0.0);
    }
}

