package org.usfirst.frc.team991.robot.commands.auto;

import org.usfirst.frc.team991.robot.Robot;
import org.usfirst.frc.team991.robot.commands.HoldCube;
import org.usfirst.frc.team991.robot.commands.shootCube;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class StraightScale extends CommandGroup {

    public StraightScale() {
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
    	if(Robot.gameData != null) {
    	String gameData = Robot.gameData;
		
    	//
    	addSequential(new DriveStraight(0.55,3.65));
    	addSequential(new gyroTurn(0.5,-85));
    //	addSequential(new WaitCommand(5));
    	
    	
    	if(gameData == null) {
    		System.out.println("NO GAME DATA FOUND");
    	}
    	
    	
    	System.out.println("GAME DATA VALUES FOUND: " + gameData);
    	if(gameData.charAt(1) == 'L'){
    		addSequential(new DriveStraight(0.4,1));	
    	} else if(gameData.charAt(1) == 'R'){
    		addSequential(new DriveStraight(0.55,(3.45*0.75)));
    	}
    		
    	addSequential(new WaitCommand(0.5));
		addSequential(new DriveStraight(-0.65,0.3));
		//addSequential(new WaitCommand(0.1));
		addSequential(new DriveStraight(0.65,0.3));
		addSequential(new HoldCube());
		addSequential(new WaitCommand(0.5));
		addSequential(new raiseArm(0.75,3.3));
		addSequential(new gyroTurn(0.5,75));
		addSequential(new DriveStraight(0.4,0.95));
		addSequential(new shootCube());
    	
    	
    	
    	
    	//addSequential(new DriveStraight(0.25,1));
    	//addSequential(new DriveStraight(0.35,1));
    	//addSequential(new DriveStraight(0.55,1.5));
    	
    	//90 degree turn
    	/*while(Robot.drivetrain.getGyro().getAngleX()<90) {
    		addSequential(new autoDrive(0.0,0.2,0.01));
    	}*/
		
    	}
    }
}
