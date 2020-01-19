/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * An example command that uses an example subsystem.
 */
public class shoot extends CommandBase {
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  //int speed = (int)SmartDashboard.getNumber("speed", 0);

  private double speed;
  public shoot() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements( Robot.shooter);
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
    leftStick = -1 * Robot.oi.xbox0.getRawAxis(4);
		// rightStick = Robot.oi.xbox0.getRawAxis(Robot.oi.XBOX_R_XAXIS);

		// if(Math.abs(leftStick) < .07)
		// {
		// 	leftStick = 0;
		// }
		// if(Math.abs(rightStick) < .07)
		// {
		// 	rightStick = 0;
    // }
    
    Robot.shooter.run(leftStick);
    SmartDashboard.putNumber("Axis Number", leftStick);
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
