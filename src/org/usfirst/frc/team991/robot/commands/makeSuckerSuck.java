package org.usfirst.frc.team991.robot.commands;

import org.usfirst.frc.team991.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class makeSuckerSuck extends Command {

    public makeSuckerSuck() {
        //requires(Robot.arm);
    	requires(Robot.sucker);
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	//Robot.arm.moveArmPower(-Robot.oi.getGamepad1().getRawAxis(1));\
    	double moveValue = Robot.oi.getGamepad0().getRawAxis(3) - Robot.oi.getGamepad0().getRawAxis(2);
    	Robot.sucker.suckMove(moveValue, moveValue , false);
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
