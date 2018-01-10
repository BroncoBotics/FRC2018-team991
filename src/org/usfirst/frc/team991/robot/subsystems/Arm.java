package org.usfirst.frc.team991.robot.subsystems;

import org.usfirst.frc.team991.robot.RobotMap;

import com.ctre.p

import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Arm extends Subsystem {
		
	TalonSRX actLeft = new TalonSRX(RobotMap.actuatorLeft);
	
    public void initDefaultCommand() {
    }
    
    public void open() {
    	actLeft.set(1.0);
    }

    public void close() {
    	actLeft.set(-1.0);
    }

    public void stop() {
    	actLeft.set(0.0);
    }
}

