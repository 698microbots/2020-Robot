/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.kauailabs.navx.frc.AHRS;
;

public class Drive extends SubsystemBase {
  // Create variables
  private static Constants consts = new Constants();
  public static TalonFX FrontRight = new TalonFX(consts.FrontRight);
  public static TalonFX FrontLeft = new TalonFX(consts.FrontLeft);
  public static TalonFX BackRight = new TalonFX(consts.BackRight);
  public static TalonFX BackLeft = new TalonFX(consts.BackLeft);

  // Create NavX
  public static AHRS navx = new AHRS(SPI.Port.kMXP);


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

  // Set Speeds on Drive Train (Tank Drive)
  public void setSpeed(double speed, double speed1) {
    // Ensures speed and speed1 are within range [-1,1]
		if(speed<-1) speed =-1;
    if(speed>1) speed=1;
    if(speed1<-1) speed1 =-1;
    if(speed1>1) speed1=1;

    SmartDashboard.putNumber("ROLL: ", navx.getRoll());
    if(navx.getRoll() <= -10){
      BackLeft.set(TalonFXControlMode.PercentOutput,-0.15);
      FrontLeft.set(TalonFXControlMode.PercentOutput,-0.15);
      BackRight.set(TalonFXControlMode.PercentOutput,0.15);
		  FrontRight.set(TalonFXControlMode.PercentOutput,0.15);
    }
    else if(navx.getRoll() >= 10){
      BackLeft.set(TalonFXControlMode.PercentOutput,0.15);
      FrontLeft.set(TalonFXControlMode.PercentOutput,0.15);
      BackRight.set(TalonFXControlMode.PercentOutput,-0.15);
		  FrontRight.set(TalonFXControlMode.PercentOutput,-0.15);
    }
    else{
      BackLeft.set(TalonFXControlMode.PercentOutput,speed);
      FrontLeft.set(TalonFXControlMode.PercentOutput,speed);
      BackRight.set(TalonFXControlMode.PercentOutput,speed1);
		  FrontRight.set(TalonFXControlMode.PercentOutput,speed1);
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  // Get Encoder Values from Drive Train motors
  public void getDriveValues(){
    SmartDashboard.putNumber("Front Right Speed: ", FrontRight.getSelectedSensorVelocity(0)* 600 / 2048);
    SmartDashboard.putNumber("Front Left Speed: ", FrontLeft.getSelectedSensorVelocity(0)* 600 / 2048);
    SmartDashboard.putNumber("Back Right Speed: ", BackRight.getSelectedSensorVelocity(0)* 600 / 2048);
    SmartDashboard.putNumber("Back Left Speed: ", BackLeft.getSelectedSensorVelocity(0)* 600 / 2048);
  }

  public void resetEncoders(){
    FrontRight.setSelectedSensorPosition(0);
    FrontLeft.setSelectedSensorPosition(0);
    BackRight.setSelectedSensorPosition(0);
    BackLeft.setSelectedSensorPosition(0);
  }

  // Retrieve Position of frontright motor
  // TODO: Double check these calcs are correct
  public double getPosition(){
    return FrontRight.getSelectedSensorPosition(0)*64.0/4096.0;
  }
}
