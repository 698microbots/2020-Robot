/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Turret;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class TurretStabilizer extends CommandBase {
  // Create NavX
  public static AHRS navx;

  // Placeholder for angle
  private double angle;

  /**
   * Creates a new TurretStabilizer.
   */
  public TurretStabilizer() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.turret);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // Set port
    navx = new AHRS(SPI.Port.kMXP);
  }
    
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Ensures the range is within 0, 360
    angle = navx.getAngle()>=360 ? navx.getAngle() % 360:navx.getAngle();

    // For Testing Purposes
    if(angle >= 90) angle = 0;

    Robot.turret.rotate(AutoAim.setPoint - angle);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
