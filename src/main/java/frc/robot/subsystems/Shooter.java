/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

public class Shooter extends SubsystemBase {
  private static Constants consts = new Constants();
  // change Falcon ID
  //public static TalonFX shooter = new TalonFX(0);
  public static TalonFX shooter = new TalonFX(consts.shooter);
  public static TalonFX indexer = new TalonFX(8);
  //public static VictorSP indexer = new VictorSP(0);
  //public static VictorSP shooter = new VictorSP(consts.shooter1);
  //public static VictorSP shooter1 = new VictorSP(consts.shooter2);
  //public static TalonSRX shooter1 = new TalonSRX(consts.shooter1);
  //public static TalonSRX shooter2 = new TalonSRX(consts.shooter2);

  public final int kPIDLoopIdx = 0;
  public final int kTimeoutMs = 30;

  public  double kF = consts.shooterkf;
  public  double kD = 0;
  public  double kI = consts.shooterkI;
  public  double kP = consts.shooterkp;

  public static PowerDistributionPanel pdp = new PowerDistributionPanel();
  //private static int CurrentCounter = 0;

  public void initDefaultCommand() {
  }

  public Shooter() {
    shooter.configFactoryDefault();
    shooter.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, kPIDLoopIdx, kTimeoutMs);

    shooter.setSensorPhase(true);
    // SmartDashboard.putNumber("Setpoint", 500);
    // SmartDashboard.putNumber("P: ", 0);
    // SmartDashboard.putNumber("I: ", 0);
    // SmartDashboard.putNumber("D: ", 0);
    // SmartDashboard.putNumber("F: ", 0);
    SmartDashboard.putString("Notes", "Hey guys, there is like a two to one gear ratio so don't like go sicko mode");
  }
  public void run(final double speed)
  {
    // if(speed <= 0.05)
    // {
    //   speed = 0;
    // }
    
    // if(pdp.getCurrent(3) < 60)
    // {
    //   if(CurrentCounter > 0) CurrentCounter--;
    //   if(CurrentCounter == 0){
    //     shooter.set(ControlMode.PercentOutput, speed);
    //     //shooter1.set(speed);
    //   }
    //   SmartDashboard.putBoolean("Stop: ", false);
    // }else{
    //   CurrentCounter++;
    //   if(CurrentCounter > 10)
    //   {
    //     CurrentCounter = 100;
    //     SmartDashboard.putBoolean("Stop: ", true);
    //     shooter.set(ControlMode.PercentOutput, 0.0);
    //     //shooter1.set(0.0);
    //   }  
    // }
    // SmartDashboard.putNumber("encoder: ", shooter.getSelectedSensorVelocity(0));
    // SmartDashboard.putNumber("Current Counter: ", CurrentCounter);
    
    kI = SmartDashboard.getNumber("I: ", 0);
    kP = SmartDashboard.getNumber("P: ", 0);
    kF = SmartDashboard.getNumber("F: ", 0);
    kD = SmartDashboard.getNumber("D: ", 0);

    shooter.configNominalOutputForward(0, kTimeoutMs);
    shooter.configNominalOutputReverse(0, kTimeoutMs);
    shooter.configPeakOutputForward(1, kTimeoutMs);
    shooter.configPeakOutputReverse(-1, kTimeoutMs);

    shooter.config_kF(kPIDLoopIdx, kF, kTimeoutMs);
    shooter.config_kD(kPIDLoopIdx, kD, kTimeoutMs);
    shooter.config_kI(kPIDLoopIdx, kI, kTimeoutMs);
    shooter.config_kP(kPIDLoopIdx, kP, kTimeoutMs);

    shooter.setInverted(true);
    shooter.set(ControlMode.Velocity, speed * 4096 / 600);
    //shooter2.follow(shooter);

    // shooter1.setInverted(true);
    // shooter1.set(ControlMode.PercentOutput,speed);
    }

  public void norun()
  {
    //shooter.set(ControlMode.Velocity,0);
    shooter.set(ControlMode.PercentOutput,0);
    //shooter2.follow(shooter1);
    //shooter1.set(0);
  }
public void index()
{
  //indexer.set(-.5);
  indexer.set(TalonFXControlMode.PercentOutput , -0.1);
}
public void indexstop()
{
  indexer.set(TalonFXControlMode.PercentOutput , 0);
  //indexer.set(0);
}

public void getStuff()
{
  SmartDashboard.putNumber("Velocity: ", shooter.getSelectedSensorVelocity(0)* 600 / 4096);
  //SmartDashboard.putNumber("I: ", kI);
  //SmartDashboard.putNumber("P: ", kP);
}
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
