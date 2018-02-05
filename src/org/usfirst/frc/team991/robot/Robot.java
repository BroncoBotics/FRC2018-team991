/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team991.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.text.DecimalFormat;

import org.usfirst.frc.team991.robot.commands.auto.DriveStraight;
import org.usfirst.frc.team991.robot.commands.auto.NullOp;
import org.usfirst.frc.team991.robot.commands.auto.StraightSwitch;
import org.usfirst.frc.team991.robot.subsystems.Arm;
import org.usfirst.frc.team991.robot.subsystems.Drivetrain;
import org.usfirst.frc.team991.robot.subsystems.Pneumatics;
import org.usfirst.frc.team991.robot.subsystems.Sucker;

public class Robot extends IterativeRobot {
	public static Drivetrain drivetrain;
	public static Arm arm;
	public static Pneumatics pneumatics;
	public static OI oi;
	public static Sucker sucker;
	
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	@Override
	public void robotInit() {
		drivetrain = new Drivetrain();
		arm = new Arm();
		pneumatics = new Pneumatics();
		oi = new OI();
		sucker = new Sucker();
		
		
		
		drivetrain.calibrateGyro();
		drivetrain.resetGryo();
		
		chooser.addDefault("No Auto", new NullOp());
		chooser.addObject("Drive Straight", new DriveStraight(0.75,4));
		chooser.addObject("Switch is straight ahead", new StraightSwitch());
		SmartDashboard.putData("Auto Mode", chooser);
		
		
		SmartDashboard.putData(drivetrain);
		SmartDashboard.putData(arm);
		
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
		DecimalFormat df = new DecimalFormat("##.##");
		SmartDashboard.putString("Gyro-X", df.format(drivetrain.getGyro().getAngleX()));
	    SmartDashboard.putString("Gyro-Y", df.format(drivetrain.getGyro().getAngleY()));
	    SmartDashboard.putString("Gyro-Z", df.format(drivetrain.getGyro().getAngleZ()));
	    
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
		
		autonomousCommand = chooser.getSelected();

		if (autonomousCommand != null)
			autonomousCommand.start();
		
		Robot.drivetrain.resetGryo();
		SmartDashboard.putData(drivetrain);
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
