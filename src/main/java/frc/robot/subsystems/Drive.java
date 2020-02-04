/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
;

public class Drive extends SubsystemBase {
  // Create variables
  private static Constants consts = new Constants();
  public static TalonFX FrontRight = new TalonFX(consts.FrontRight);
  public static TalonFX FrontLeft = new TalonFX(consts.FrontLeft);
  public static TalonFX BackRight = new TalonFX(consts.BackRight);
  public static TalonFX BackLeft = new TalonFX(consts.BackLeft);


  public Drive()   {
    // Config Encoders
    FrontRight.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    FrontLeft.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    BackRight.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    BackLeft.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
  }

  // Set Right Speeds on Drive Train
  public void setRightSpeed(double speed) {
    speed = -speed;
		if(speed<-1) speed =-1;
		if(speed>1) speed=1;
    BackRight.set(TalonFXControlMode.PercentOutput,speed);
    FrontRight.set(TalonFXControlMode.PercentOutput,speed);
  }

  // Set Left Speeds on Drive Train
  public void setLeftSpeed(double speed) {
		if(speed<-1) speed =-1;
		if(speed>1) speed=1;
		BackLeft.set(TalonFXControlMode.PercentOutput,speed);
		FrontLeft.set(TalonFXControlMode.PercentOutput,speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  // Get Encoder Values from Drive Train motors
  public void getDriveValues()
  {
    SmartDashboard.putNumber("Front Right Speed: ", FrontRight.getSelectedSensorVelocity(0)* 600 / 2048);
    SmartDashboard.putNumber("Front Left Speed: ", FrontLeft.getSelectedSensorVelocity(0)* 600 / 2048);
    SmartDashboard.putNumber("Back Right Speed: ", BackRight.getSelectedSensorVelocity(0)* 600 / 2048);
    SmartDashboard.putNumber("Back Left Speed: ", BackLeft.getSelectedSensorVelocity(0)* 600 / 2048);
  }
}
