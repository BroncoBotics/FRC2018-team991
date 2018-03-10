package org.usfirst.frc.team991.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Pneumatics extends Subsystem {
	
	private Compressor compressor = new Compressor();
	//private AnalogInput psiSensor = new AnalogInput(0);
	//private DoubleSolenoid pop = new DoubleSolenoid(0,1);
	private DoubleSolenoid hold = new DoubleSolenoid(4,5);
	private DoubleSolenoid punch = new DoubleSolenoid(0,1);
	
	
	public Pneumatics() {
		
	}
	
    public void initDefaultCommand() {}
    
    public void start() {
		compressor.start();
	}
    
    public void getPSI() {
    	//return 50 * psiSensor.getAverageVoltage() - 25 ;
    }
    public Compressor getComp() {
    	return compressor;
    }
    public void holdCube() {
    	hold.set(DoubleSolenoid.Value.kReverse);
    }
    public void releaseCube() {
    	hold.set(DoubleSolenoid.Value.kForward);
    }
    public void pullCube() {
    	punch.set(DoubleSolenoid.Value.kForward);
    }
    public void punchCube() {
    	punch.set(DoubleSolenoid.Value.kReverse);
    }
    
    public enum GearSetting {
    	HOLD,RELEASE,NONE
    }
}

