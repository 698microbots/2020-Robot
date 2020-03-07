/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Turret;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Turret;
public class AutoAim extends CommandBase {
  double kp = .25;
	double sp_max = .05;
  double sp = 0.025;
  boolean updated = false;
  double x;
  /**
   * Creates a new AutoAim.
   */
  public AutoAim() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.turret);
    addRequirements(Robot.vision);
    SmartDashboard.putNumber("TurretSetpoint", 10);
    // SmartDashboard.putNumber("Shooter kP: ", .25);
    // SmartDashboard.putNumber("Shooter sp: ", 0.05);
    // SmartDashboard.putNumber("Shooter sp_max: ", 0.025);
    // kp = SmartDashboard.getNumber("Shooter kP: ", .25);
    // sp = SmartDashboard.getNumber("Shooter sp: ", 0.05);
    // sp_max = SmartDashboard.getNumber("Shooter sp_max: ", 0.025);
    Robot.turret.Turret.setSelectedSensorPosition(0);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    updated = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    // kp = SmartDashboard.getNumber("Shooter kP: ", .25);
    // sp = SmartDashboard.getNumber("Shooter sp: ", 0.05);
    // sp_max = SmartDashboard.getNumber("Shooter sp_max: ", 0.1);
    //double x = SmartDashboard.getNumber("TurretSetpoint", 10);
    //x += Robot.turret.GetPosition();
    if(updated == false){
      x = -Robot.vision.GetX(); 
      updated = true;
    }
    SmartDashboard.putNumber("X Limelight", x);
    Robot.turret.rotate(x);
    // if (x > 0)
    // {
    //   SmartDashboard.putNumber("Rotation Value", ((sp - x*kp)<sp_max?(sp - x*kp):sp_max));
    //   Robot.turret.rotate(Math.max(-1*((sp - x*kp)<sp_max?(sp - x*kp):sp_max),0));
    // }else
    // {
    //   Robot.turret.rotate(Math.min(((sp - x*kp)<sp_max?(sp - x*kp):sp_max),sp_max));
    // }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(Robot.turret.GetSpeed() <= 0.005)
    {
      return true;
    }
    else return false;
  }
}
