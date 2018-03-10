/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team991.robot;

import org.usfirst.frc.team991.robot.commands.HoldCube;
import org.usfirst.frc.team991.robot.commands.PullPunch;
import org.usfirst.frc.team991.robot.commands.Punch;
import org.usfirst.frc.team991.robot.commands.ReleaseCube;
import org.usfirst.frc.team991.robot.commands.popControl;
import org.usfirst.frc.team991.robot.commands.resetGyro;
import org.usfirst.frc.team991.robot.commands.shootCube;
import org.usfirst.frc.team991.robot.subsystems.Pneumatics.GearSetting;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);
	
	Joystick gamepad0 = new Joystick(0);
	Joystick gamepad1 = new Joystick(1);
	
	Button button_a = new JoystickButton(gamepad1, 1),
			button_b = new JoystickButton(gamepad1, 2),
			button_x = new JoystickButton(gamepad1, 3),
			button_y = new JoystickButton(gamepad1, 4),
			button_rb = new JoystickButton(gamepad1, 6);
	
	public Joystick getGamepad0(){
		return gamepad0;
	}
	public Joystick getGamepad1(){
		return gamepad1;
	}
	
	public OI() {
		//button_x.whenPressed(new shootCube());
		
		button_a.whenPressed(new ReleaseCube());
		button_b.whenPressed(new HoldCube());
		
		button_x.whenPressed(new PullPunch());
		button_y.whenPressed(new Punch());
		
		button_rb.whenPressed(new shootCube());
		
		
		
	}
	
	
	
	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.
 
	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}
