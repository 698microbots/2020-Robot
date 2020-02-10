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

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


;

public class Turret extends SubsystemBase {
  private static Constants consts = new Constants();
  public static TalonFX Turret = new TalonFX(consts.Turret);


  public void initDefaultCommand() {
  }

  public Turret() 
  {
    Turret.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);  
  }
  public void rotate(double speed)
  {
    
    //if((Turret.getSelectedSensorPosition(0) > 100 && speed > 0) || (Turret.getSelectedSensorPosition(0) < -100 && speed < 0) )
    //{
     // Turret.set(TalonFXControlMode.PercentOutput , 0);
    //}
    //else
    //{
      Turret.set(TalonFXControlMode.PercentOutput , speed );
    //}
  }
  public void GetPosition()
  {
    SmartDashboard.putNumber("position", Turret.getSelectedSensorPosition(0) );
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
