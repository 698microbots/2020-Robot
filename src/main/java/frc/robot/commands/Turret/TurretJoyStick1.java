/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Turret;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class TurretJoyStick1 extends CommandBase {
  /**
   * Creates a new rotateLeft.
   */
  public TurretJoyStick1() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.turret);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }
  double speed;
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    /*
    speed =  Robot.oi.xbox0.getRawAxis(Robot.oi.XBOX_L_XAXIS);
    //dead band
	  if(Math.abs(speed) < .12)
		{
		 	speed = 0;
		}
    Robot.turret.rotate(speed);
    */
    Robot.turret.rotate(-.06);
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
