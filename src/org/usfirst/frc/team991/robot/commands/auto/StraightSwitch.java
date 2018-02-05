package org.usfirst.frc.team991.robot.commands.auto;

import org.usfirst.frc.team991.robot.commands.ReleaseCube;
import org.usfirst.frc.team991.robot.commands.popControl;
import org.usfirst.frc.team991.robot.subsystems.Pneumatics.GearSetting;


import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class StraightSwitch extends CommandGroup {

    public StraightSwitch() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
    	
    	addParallel(new DriveStraight(0.5,0.35));
    	addSequential(new raiseArm(0.4,1.5));
    	
    	addSequential(new DriveStraight(0.4,0.1));
    	addSequential(new DriveStraight(0.25,0.1));
    	addSequential(new DriveStraight(0.15,0.1));
    	addSequential(new DriveStraight(0.05,0.1));
    	
    	addSequential(new WaitCommand(0.5));
    	
		if(gameData.charAt(0) == 'L'){
			addSequential(new autoDrive(0.0,0.15,0.5));
			addSequential(new DriveStraight(0.3,1));
			addSequential(new autoDrive(0.0,-0.15,0.5));
			
			
		} else if(gameData.charAt(0) == 'R'){
			addSequential(new autoDrive(0.0,-0.15,0.5));
			addSequential(new DriveStraight(0.3,1));
			addSequential(new autoDrive(0.0,0.15,0.5));
			
		}
		addSequential(new ReleaseCube());
    	
    	
    }
}
