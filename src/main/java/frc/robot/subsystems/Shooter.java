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
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

public class Shooter extends SubsystemBase {
  private static Constants consts = new Constants();
  public static TalonFX shooter = new TalonFX(consts.shooter);
  public static VictorSPX indexer = new VictorSPX(consts.indexer);

  public final int kPIDLoopIdx = 0;
  public final int kTimeoutMs = 30;

  public  double kF = SmartDashboard.getNumber("Shoot F : ", 0);
  public  double kD = 0;
  public  double kI = SmartDashboard.getNumber("Shoot I: ", 0);
  public  double kP = SmartDashboard.getNumber("Shoot P: ", 0);

  public static PowerDistributionPanel pdp = new PowerDistributionPanel();
  private static int CurrentCounter = 0;

  public void initDefaultCommand() {
  }

  public Shooter() {
    shooter.configFactoryDefault();
    shooter.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, kPIDLoopIdx, kTimeoutMs);

    shooter.setSensorPhase(true);
    // SmartDashboard.putNumber("Setpoint", 500);
    SmartDashboard.putNumber("Shoot P: ", 0);
    SmartDashboard.putNumber("Shoot I: ", 0);
    SmartDashboard.putNumber("Shoot D: ", 0);
    SmartDashboard.putNumber("Shoot F: ", 0);
    SmartDashboard.putString("Notes", "Hey guys, there is like a two to one gear ratio so don't like go sicko mode");
  }
  public void run(final double speed){
    
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

    kP = SmartDashboard.getNumber("Shoot P: ", 0);
    kI = SmartDashboard.getNumber("Shoot I: ", 0);
    kD = SmartDashboard.getNumber("Shoot D: ", 0);
    kF = SmartDashboard.getNumber("Shoot F: ", 0);

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
  }

  public void norun(){
    shooter.set(ControlMode.PercentOutput,0);
  }

  public void index(double speed){
    indexer.set(ControlMode.PercentOutput , speed);
  }

  public void indexstop(){
    indexer.set(ControlMode.PercentOutput , 0);
  }

  public void getVelocity(){
    SmartDashboard.putNumber("Shooter Velocity: ", shooter.getSelectedSensorVelocity(0)* 600 / 4096);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

}
