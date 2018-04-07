package org.usfirst.frc.team991.robot.commands.auto;

import org.usfirst.frc.team991.robot.Robot;
import org.usfirst.frc.team991.robot.commands.HoldCube;
import org.usfirst.frc.team991.robot.commands.ReleaseCube;
import org.usfirst.frc.team991.robot.commands.popControl;
import org.usfirst.frc.team991.robot.commands.shootCube;
import org.usfirst.frc.team991.robot.subsystems.Pneumatics.GearSetting;


import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class StraightSwitch2 extends CommandGroup {

	static String gameData;
	
    public StraightSwitch2() {
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
    	
		
		gameData = Robot.gameData;
    	
    	
    	
    	if(gameData != null) {
    		if(gameData.charAt(0) == 'R'){
    			addSequential(new DriveStraight(0.55,0.8));
    			
    			addSequential(new WaitCommand(5));
    			addSequential(new HoldCube());
            	addSequential(new WaitCommand(0.5));
            	addSequential(new raiseArm(0.70,1.8));
        		addSequential(new DriveStraight(0.45,0.8));
        		addSequential(new shootCube());
    		} else if(gameData.charAt(0) == 'L'){
    			addSequential(new DriveStraight(0.55,0.8));
    			addSequential(new DriveStraight(0.45,0.8));
    		}
    		
        	
    	}else {
    		System.out.println("NO GAME DATA");
    	}
    	
    	
    	
    }
}
