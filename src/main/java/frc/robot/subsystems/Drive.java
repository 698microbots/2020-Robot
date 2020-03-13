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
import edu.wpi.first.wpilibj.kinematics;
import edu.wpi.first.wpilibj.geometry;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.kauailabs.navx.frc.AHRS;

public class Drive extends SubsystemBase {
  // Create variables
  private static Constants consts = new Constants();
  public static TalonFX FrontRight = new TalonFX(consts.FrontRight);
  public static TalonFX FrontLeft = new TalonFX(consts.FrontLeft);
  public static TalonFX BackRight = new TalonFX(consts.BackRight);
  public static TalonFX BackLeft = new TalonFX(consts.BackLeft);

  // Create NavX
  public static AHRS navx = new AHRS(SPI.Port.kMXP);
	
  // Create Differential Drive Odometry Object and pose
  DifferentialDriveOdometry m_odometry;
  Pose2d m_pose = new Pose2d(consts.InitalX, consts.InitialY, new Rotation2d());

  public Drive()   {
    // Config Encoders
    FrontRight.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    FrontLeft.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    BackRight.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    BackLeft.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    
    //we must reset encoders before instantiating the odometry
    resetEncoders();
    
    //create odometry
    m_odometry = new DifferentialDriveOdometry(new Rotation2d(), new Pose2d(consts.InitalX, consts.InitialY, new Rotation2d());
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
    
    // Don't exactly know if it's Yaw or Pitch
    SmartDashboard.putNumber("YAW: ", navx.getYaw());
    Rotation2d gyroAngle = Rotation2d.fromDegrees(-navx.getYaw());
    
    //Average position of the two encoders on each side
    double right_position = ((FrontRight.getSelectedSensorPosition(0)*64.0/4096.0) + (BackRight.getSelectedSensorPosition(0)*64.0/4096.0))/2;
    double left_position = ((FrontLeft.getSelectedSensorPosition(0)*64.0/4096.0) + (BackLeft.getSelectedSensorPosition(0)*64.0/4096.0))/2;
    
    // Update Odometry
    m_pose = m_odometry.update(gyroAngle, left_position, right_position);
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
  public Pose2d getPosition(){
    return m_pose;
  }
}
