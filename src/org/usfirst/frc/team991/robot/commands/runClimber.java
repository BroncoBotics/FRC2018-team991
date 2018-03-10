package org.usfirst.frc.team991.robot.commands;

import org.usfirst.frc.team991.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class runClimber extends Command {

	private double moveValue;
    public runClimber() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	moveValue = 0.0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	moveValue = Robot.oi.getGamepad1().getRawAxis(5);
    	if(moveValue > 0) {
    		Robot.climber.moveClimber(moveValue*moveValue);
    	} else if(moveValue < 0) {
    		Robot.climber.moveClimber(moveValue*-moveValue);
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
