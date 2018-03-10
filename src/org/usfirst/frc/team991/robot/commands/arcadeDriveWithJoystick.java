package org.usfirst.frc.team991.robot.commands;

import java.text.DecimalFormat;

import org.usfirst.frc.team991.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class arcadeDriveWithJoystick extends Command {

    public arcadeDriveWithJoystick() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	DecimalFormat df = new DecimalFormat("#.##");
    	
    	double left = -Robot.oi.getGamepad0().getRawAxis(1);
    	
    	SmartDashboard.putString("Driver 1 : Left Stick Y", df.format(left));
    	SmartDashboard.putString("Driver 1 : Right Stick X", df.format(Robot.oi.getGamepad0().getRawAxis(4)));
    	//Robot.drivetrain.tankDrive(Robot.oi.getGamepad0().getRawAxis(1), Robot.oi.getGamepad0().getRawAxis(5));
    	
    	Robot.drivetrain.arcadeDrive(left, Robot.oi.getGamepad0().getRawAxis(4), true);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//Robot.drivetrain.arcadeDrive(0.0, 0.0, false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
