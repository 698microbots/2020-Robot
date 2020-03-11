/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Robot;

public class PickUp extends CommandBase {
  /**
   * Creates a new PickUp.
   */
  public PickUp() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.intake);
    addRequirements(Robot.shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }
  double leftStick;
  double Trigger;
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    leftStick = Robot.oi.xbox1.getRawAxis(Robot.oi.XBOX_L_YAXIS);
    Trigger = Robot.oi.xbox0.getRawAxis(Robot.oi.XBOX_L_Trigger);

    while(Robot.oi.xbox0.getBumper(Hand.kLeft)) {
      Robot.intake.retrieveBall(0.3);
      Robot.intake.upserializer1(0.5);
    }

    Robot.intake.retrieveBall(0);
    Robot.intake.upserializer1(0);

    if(leftStick > 0.1 || leftStick < -0.1) {
      Robot.intake.upserializer2(leftStick);
      Robot.shooter.index(leftStick);
    }
    else {
      Robot.intake.upserializer2(0);
      Robot.shooter.index(0);
    }
    
    Robot.intake.retrieveBall(-0.3*Trigger);
    Robot.intake.upserializer1(-0.5*Trigger);
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
