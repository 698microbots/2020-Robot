/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class PickUp extends CommandBase {
  /**
   * Creates a new PickUp.
   */
  public PickUp() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }
  double speedL;
  double speedR;
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    speedL = Robot.oi.xbox0.getRawAxis(Robot.oi.XBOX_L_Trigger);
    Robot.intake.retrieveBall(speedL);
    Robot.intake.upserializer(speedL);
    speedR = Robot.oi.xbox0.getRawAxis(Robot.oi.XBOX_R_Trigger);
    Robot.intake.outtakeBall(speedR);
    Robot.intake.downserializer(speedR);
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
