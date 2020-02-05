/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {

  /**
   * Creates Intake Subsystem.
   */
  private static Constants consts = new Constants();
  public static TalonFX intake = new TalonFX(consts.intake);

  public Intake() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void retrieveBall(double speed)
  {
    if(speed > 0)
      intake.set(TalonFXControlMode.PercentOutput, .5);
    else
      intake.set(TalonFXControlMode.PercentOutput, 0);
  }

  public void outtakeBall(double speed)
  {
    if(speed > 0)
    intake.set(TalonFXControlMode.PercentOutput, -.5);
    else
      intake.set(TalonFXControlMode.PercentOutput, 0);
  }
}
