/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Drive;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class JoyStickDrive extends CommandBase {
  /**
   * Creates the JoyStick Command.
   *
   * @param subsystem The subsystem used by this command.
   */
  public JoyStickDrive() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  double leftStick;
  double rightStick;
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Mapping for xbox controller
    leftStick = -1* Robot.oi.xbox0.getRawAxis(Robot.oi.XBOX_L_YAXIS);
	  rightStick = Robot.oi.xbox0.getRawAxis(Robot.oi.XBOX_R_XAXIS);

    // Deadband
		if(Math.abs(leftStick) < .1)
		{
		 	leftStick = 0;
		}
		if(Math.abs(rightStick) < .1)
		{
		 	rightStick = 0;
    }
  
    // Set Motor Speed
		Robot.drive.setLeftSpeed((leftStick + rightStick));
    Robot.drive.setRightSpeed((leftStick - rightStick));
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
