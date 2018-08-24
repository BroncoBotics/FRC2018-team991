/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team991.robot;

//import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.text.DecimalFormat;

import org.usfirst.frc.team991.robot.commands.HoldCube;
import org.usfirst.frc.team991.robot.commands.shootCube;
import org.usfirst.frc.team991.robot.commands.auto.DriveStraight;
import org.usfirst.frc.team991.robot.commands.auto.NullOp;
import org.usfirst.frc.team991.robot.commands.auto.StraightScale;
import org.usfirst.frc.team991.robot.commands.auto.StraightSwitch;
import org.usfirst.frc.team991.robot.commands.auto.StraightSwitch2;
import org.usfirst.frc.team991.robot.commands.auto.gyroTurn;
import org.usfirst.frc.team991.robot.subsystems.Arm;
import org.usfirst.frc.team991.robot.subsystems.Climber;
import org.usfirst.frc.team991.robot.subsystems.Drivetrain;
import org.usfirst.frc.team991.robot.subsystems.Pneumatics;
import org.usfirst.frc.team991.robot.subsystems.Sucker;

public class Robot extends IterativeRobot {
	public static Drivetrain drivetrain;
	public static Arm arm;
	public static Pneumatics pneumatics;
	public static OI oi;
	public static Sucker sucker;
	public static Climber climber;
	public static String gameData;
	
	private double gAngle = 0;
	
	private int autoCheck = 0;
	
	Command autonomousCommand;
	SendableChooser<String> chooser = new SendableChooser<>();

	@Override
	public void robotInit() {
		drivetrain = new Drivetrain();
		arm = new Arm();
		pneumatics = new Pneumatics();
		oi = new OI();
		sucker = new Sucker();
		climber = new Climber();
		
		
		
		drivetrain.calibrateGyro();
		drivetrain.resetGryo();
		/*
		chooser.addDefault("No Auto", new NullOp());
		//addSequential(new DriveStraight(0.75,2.65));
		chooser.addObject("Drive Straight - Far Left Position", new DriveStraight(0.75,2.65));
		//chooser.addObject("Hold Cube test", new HoldCube());
		//chooser.addObject("Shoot Cube test", new shootCube());
		chooser.addObject("Switch - Close Left Position", new StraightSwitch());
		chooser.addObject("Scale - Far Left Position", new StraightScale());
		*/
		chooser.addDefault("No Auto", "No Auto");
		chooser.addObject("Drive Straight NO SWITCH", "Drive Straight");
		chooser.addObject("Switch RIGHT", "Switch");
		chooser.addObject("Switch LEFT", "Zach");
		//chooser.addObject("Zach's switch", "Zach");
		SmartDashboard.putData("Auto Mode", chooser);
		
		
		
		
		//CameraServer.getInstance().startAutomaticCapture();
		
		/*UsbCamera camera = new UsbCamera("cam0",0);
		camera.setFPS(22);
		camera.setResolution(640, 480); //320 = width, 240 = height
		CameraServer.getInstance().startAutomaticCapture(camera);*/
		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}
	
	@Override
	public void robotPeriodic(){
		SmartDashboard.putData("Auto Mode", chooser);
		/*autoCheck+=1;
		if(autoCheck == 75) {
			SmartDashboard.putData("Auto Mode", chooser);
			//System.out.println(chooser.getSelected());
			drivetrain.readUsage();
			autoCheck = 0;
		}*/
		//System.out.println(chooser.getSelected().getName());
	    
	    
	    
	}
	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		autonomousCommand = new NullOp();
		if(chooser.getSelected().equals("No Auto")) {
			autonomousCommand = new NullOp();
		}else if(chooser.getSelected().equals("Switch")) {
			autonomousCommand = new StraightSwitch();
		}else if(chooser.getSelected().equals("Scale")) {
			autonomousCommand = new StraightScale();
		}else if(chooser.getSelected().equals("Drive Straight")) {
			autonomousCommand = new DriveStraight(0.5,1.5);
		}else if(chooser.getSelected().equals("Zach")) {
			autonomousCommand = new StraightSwitch2();
		}
		
	

		if (autonomousCommand != null) {
			Robot.drivetrain.resetGryo();
			autonomousCommand.start();
		}
			
		
		
		
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		
		/*DecimalFormat df = new DecimalFormat("##.##");
		SmartDashboard.putString("Gyro-X", df.format(drivetrain.getGyro().getAngleX()));
	    SmartDashboard.putString("Gyro-Y", df.format(drivetrain.getGyro().getAngleY()));
	    SmartDashboard.putString("Gyro-Z", df.format(drivetrain.getGyro().getAngleZ()));*/
	    //SmartDashboard.putNumber("Potent", Robot.drivetrain.getPotent());
	}

	@Override
	public void teleopInit() {
		
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
	}
	
	
}
