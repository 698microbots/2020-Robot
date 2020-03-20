/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Wheel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class RotationControl extends CommandBase {

  // Count number of complete revolutions
  private double count;
  // Read initial color
  private String color;

  /**
   * Creates a new Rotation_Control.
   */
  public RotationControl() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.wheel);
    addRequirements(Robot.vision);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    color = Robot.vision.readColor();
    if (color.equals("")) throw new IllegalArgumentException();

    while(count < 4)
    {
      Robot.wheel.setSpeed(0.2);
      if(color.equals(Robot.vision.readColor())) count++;
    }

    Robot.wheel.setSpeed(0.0);
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
