/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


;

public class Turret extends SubsystemBase {
  private static Constants consts = new Constants();
  public static TalonFX Turret = new TalonFX(consts.Turret);

  public final int kPIDLoopIdx = 0;
  public final int kTimeoutMs = 30;

  public  double kF = SmartDashboard.getNumber("F: ", 0.0);//1023.0/7200.0;
  public  double kD = 0;
  public  double kI = SmartDashboard.getNumber("I: ", 0.0);
  public  double kP = SmartDashboard.getNumber("P: ", 0.0);


  public void initDefaultCommand() {
  }

  public Turret() 
  {
    Turret.configFactoryDefault();
    Turret.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, kPIDLoopIdx, kTimeoutMs);

    Turret.setSensorPhase(true);
  }
  public void rotate(double position)
  {
    
    //if((Turret.getSelectedSensorPosition(0) > 100 && speed > 0) || (Turret.getSelectedSensorPosition(0) < -100 && speed < 0) )
    //{
     // Turret.set(TalonFXControlMode.PercentOutput , 0);
    //}
    //else
    //{
    kI = SmartDashboard.getNumber("I: ", 0);
    kP = SmartDashboard.getNumber("P: ", 0);
    kF = SmartDashboard.getNumber("F: ", 0);
    kD = SmartDashboard.getNumber("D: ", 0);

    Turret.configAllowableClosedloopError(0, kPIDLoopIdx, kTimeoutMs);

    Turret.configNominalOutputForward(0, kTimeoutMs);
    Turret.configNominalOutputReverse(0, kTimeoutMs);
    Turret.configPeakOutputForward(1, kTimeoutMs);
    Turret.configPeakOutputReverse(-1, kTimeoutMs);

    // double absolutePosition = Turret.getSensorCollection().getIntegratedSensorPosition();

    // Turret.setSelectedSensorPosition(0, kPIDLoopIdx, kTimeoutMs);

    //Turret.config_kF(kPIDLoopIdx, kF, kTimeoutMs);
    //Turret.config_kD(kPIDLoopIdx, kD, kTimeoutMs);
    //Turret.config_kI(kPIDLoopIdx, kI, kTimeoutMs);
    Turret.config_kP(kPIDLoopIdx, kP, kTimeoutMs);
    
    Turret.set(ControlMode.Position, position*4096);
    //}
  }
  public double GetPosition()
  {
    SmartDashboard.putNumber("position", Turret.getSelectedSensorPosition(0));
    return Turret.getSelectedSensorPosition(0);
  }
  public void GetSpeed()
  {
    SmartDashboard.putNumber("speed", Turret.getSelectedSensorVelocity(0) * 600 / 2048 );
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
