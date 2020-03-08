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
  public static CANSparkMax serializer1 = new CANSparkMax(consts.serializer1, MotorType.kBrushless);
  public static CANSparkMax serializer2 = new CANSparkMax(consts.serializer2, MotorType.kBrushless);

  public Intake() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void retrieveBall(double speed)
  {
    intake.set(0.40*speed);
  }

  public void outtakeBall(double speed)
  {
    intake.set(0.40*-speed);
  }
  public void upserializer(double speed)
  {
    serializer1.set(0.40*-speed);
    serializer2.set(0.40*-speed);
  }
  public void downserializer(double speed)
  {
    serializer1.set(0.40*speed);
    serializer2.set(0.40*speed);
  }
}
