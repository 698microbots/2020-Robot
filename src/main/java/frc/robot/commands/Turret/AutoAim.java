/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Turret;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
public class AutoAim extends CommandBase {
  double kp = .025;
	double sp_max = .5;
  double sp = 0.25;
  double x;
  /**
   * Creates a new AutoAim.
   */
  public AutoAim() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.turret);
    addRequirements(Robot.vision);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    double x = Robot.vision.GetX();
    if (x > 0)
    {
      Robot.turret.rotate(Math.max(((sp - x*kp)<sp_max?(sp - x*kp):sp_max),0));
    }else
    {
      Robot.turret.rotate(Math.min(((sp - x*kp)<sp_max?(sp - x*kp):sp_max),sp_max));
    }
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
