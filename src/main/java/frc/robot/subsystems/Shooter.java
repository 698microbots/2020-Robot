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

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

public class Shooter extends SubsystemBase {
  private static Constants consts = new Constants();
  // change Falcon ID
  //public static TalonFX shooter = new TalonFX(0);
  //public static TalonFX shooter = new TalonFX(consts.shooter1);
  //public static TalonFX shooter2 = new TalonFX(consts.shooter2);
  public static VictorSP indexer = new VictorSP(0);
  //public static VictorSP shooter = new VictorSP(consts.shooter1);
  //public static VictorSP shooter1 = new VictorSP(consts.shooter2);
  public static TalonSRX shooter1 = new TalonSRX(consts.shooter1);
  public static TalonSRX shooter2 = new TalonSRX(consts.shooter2);

  public final int kPIDLoopIdx = 0;
  public final int kTimeoutMs = 30;

  public final double kF = 0.0;//1023.0/7200.0;
  public final int kD = 0;
  public final double kI = SmartDashboard.getNumber("I: ", 0.001);
  public final double kP = SmartDashboard.getNumber("P: ", 0.05);

  public static PowerDistributionPanel pdp = new PowerDistributionPanel();
  //private static int CurrentCounter = 0;

  public void initDefaultCommand() {
  }

  public Shooter() {
    shooter1.configFactoryDefault();
    shooter1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kPIDLoopIdx, kTimeoutMs);

    shooter1.setSensorPhase(true);
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

    shooter1.configNominalOutputForward(0, kTimeoutMs);
    shooter1.configNominalOutputReverse(0, kTimeoutMs);
    shooter1.configPeakOutputForward(1, kTimeoutMs);
    shooter1.configPeakOutputReverse(-1, kTimeoutMs);

    shooter1.config_kF(kPIDLoopIdx, kF, kTimeoutMs);
    shooter1.config_kD(kPIDLoopIdx, kD, kTimeoutMs);
    shooter1.config_kI(kPIDLoopIdx, kI, kTimeoutMs);
    shooter1.config_kP(kPIDLoopIdx, kP, kTimeoutMs);

    shooter1.setInverted(true);
    shooter1.set(ControlMode.Velocity, speed * 4096 / 600);
    shooter2.follow(shooter1);

    // shooter1.setInverted(true);
    // shooter1.set(ControlMode.PercentOutput,speed);
    }

  public void norun()
  {
    //shooter.set(ControlMode.Velocity,0);
    shooter1.set(ControlMode.Velocity, 0);
    shooter2.follow(shooter1);
    //shooter1.set(0);
  }
public void index()
{
  indexer.set(-.5);
}
public void indexstop()
{
  indexer.set(0);
}

public void getStuff()
{
  SmartDashboard.putNumber("Velocity: ", shooter1.getSelectedSensorVelocity(0)* 600 / 4096);
}
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
