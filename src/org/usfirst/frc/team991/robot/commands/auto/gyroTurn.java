package org.usfirst.frc.team991.robot.commands.auto;

import java.text.DecimalFormat;

import org.usfirst.frc.team991.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class gyroTurn extends Command {
	
	private double Angle;
	private double gAngle;
	private double powerVal;

    public gyroTurn(double power, double angle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	powerVal = power;
    	Angle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetGryo();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Angle > 0){
    		Robot.drivetrain.arcadeDrive(0.0, -powerVal, false);
    	}else {
    		Robot.drivetrain.arcadeDrive(0.0, powerVal, false);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
        gAngle = Robot.drivetrain.getGyro().getAngleX();
        
        if(Math.abs(gAngle) < (Math.abs(Angle)-5)) {
        	return false;
        } else {
        	return true;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.arcadeDrive(0.0, 0.0, false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
