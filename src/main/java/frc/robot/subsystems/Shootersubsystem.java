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
import frc.robot.commands.shoot;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

public class Shootersubsystem extends SubsystemBase {
  private static Constants consts = new Constants();
  public static TalonFX shooter = new TalonFX(consts.driveRightRearMotor);
  private double percentspeed;
  /**
   * Creates a new ExampleSubsystem.
   */

  public void initDefaultCommand() {
    setDefaultCommand(new shoot());
  }

  public Shootersubsystem() {
  }
  public void run(double speed)
  {
    //double speed = ((rpm / 60) * (2 * Math.PI)) * 2.375;
    shooter.set(ControlMode.PercentOutput,speed);
  }

  public void norun()
  {
    shooter.set(ControlMode.Velocity,0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void getStuff()
  {
    SmartDashboard.putNumber("Velocity: ", shooter.getSelectedSensorVelocity()/2048/0.1 * 60);
  }
}
