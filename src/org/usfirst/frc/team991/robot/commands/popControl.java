package org.usfirst.frc.team991.robot.commands;

import org.usfirst.frc.team991.robot.Robot;
import org.usfirst.frc.team991.robot.subsystems.Pneumatics.GearSetting;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class popControl extends InstantCommand {

	GearSetting setting;
	
    public popControl(GearSetting set) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	//requires(Robot.pneumatics);
    	this.setting = set;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	/*switch (setting) {
    	case PUSH:
    		Robot.pneumatics.pushCube();
    	case PULL:
    		Robot.pneumatics.pullCube();
    	case HOLD:
    		Robot.pneumatics.holdCube();
    	case RELEASE:
    		Robot.pneumatics.releaseCube();
    	}*/
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
