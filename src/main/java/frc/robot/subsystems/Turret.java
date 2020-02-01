/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.Drive.JoyStickDrive;

;

public class Turret extends SubsystemBase {
  private static Constants consts = new Constants();
  public static VictorSP Turret = new VictorSP(consts.Turret);


  public void initDefaultCommand() {
    setDefaultCommand(new JoyStickDrive());
  }

  public Turret() {
    
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
