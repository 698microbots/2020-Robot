/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Drive;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//import java.lang.module.ModuleDescriptor.Requires;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class AutoDrive extends CommandBase {
  /**
   * Creates a new AutoDrive.
   */
  public AutoDrive() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.drive); 
    Robot.drive.resetEncoders();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double currentPos = Robot.drive.getPosition();
    while(currentPos > -10000)
    {
      SmartDashboard.putNumber("Position: ", currentPos);
      Robot.drive.setLeftSpeed(0.1);
      Robot.drive.setRightSpeed(0.1);
      currentPos = Robot.drive.getPosition();
    }
    Robot.drive.setLeftSpeed(0.0);
    Robot.drive.setRightSpeed(0.0);
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
