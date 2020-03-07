/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.EncoderType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Intake extends SubsystemBase {

  /**
   * Creates Intake Subsystem.
   */
  private static Constants consts = new Constants();
  public static CANSparkMax intake = new CANSparkMax(consts.intake, MotorType.kBrushless);
  public static CANSparkMax serializer = new CANSparkMax(consts.searializer , MotorType.kBrushless);
  public static CANSparkMax serializer2 = new CANSparkMax(consts.searializer2, MotorType.kBrushless);

  public Intake() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void retrieveBall(double speed)
  {
    if(speed > 0)
      intake.set(.5);
    else
      intake.set(0);
  }

  public void outtakeBall(double speed)
  {
    if(speed > 0)
      intake.set(-.5);
    else
      intake.set(0);
  }
  public void upserializer(double speed)
  {
    if(speed > 0)
    {
      serializer.set(0.5);
      serializer2.set(0.5);
    }
    else
    {
      serializer.set(0);
      serializer2.set(0);
    }
  }
  public void downserializer(double speed)
  {
    if(speed > 0)
    {
      serializer.set(-0.5); 
      serializer2.set(-0.5);
    }
    else
    {
      serializer.set(0);
      serializer2.set( 0); 
    }
    CANEncoder encoder = serializer.getEncoder(EncoderType.kSensorless, 42);
    CANEncoder encoder1 = serializer2.getEncoder(EncoderType.kSensorless, 42);
    SmartDashboard.putNumber("Serializer: ", encoder.getVelocity());
    SmartDashboard.putNumber("Serializer 2: ", encoder1.getVelocity());
  }
}
