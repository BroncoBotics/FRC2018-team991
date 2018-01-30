package org.usfirst.frc.team991.robot.commands.auto;

import org.usfirst.frc.team991.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class autoDrive extends Command {

	public double powerVal1;
	public double powerVal2;
	public Boolean complete;
	
    public autoDrive(double LeftStick, double RightStick, double timeout) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	powerVal1=LeftStick;
    	powerVal2=RightStick;
    	requires(Robot.drivetrain);
    	setTimeout(timeout);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.arcadeDrive(powerVal1, powerVal2, false);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
