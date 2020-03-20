/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.Shooter.*;
import frc.robot.commands.Turret.AutoAim;
import frc.robot.commands.Turret.TurretJoyStick;
import frc.robot.commands.Wheel.PositionControl;
import frc.robot.commands.Wheel.RotationControl;
import frc.robot.commands.Drive.*;
import frc.robot.commands.Intake.countBalls;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private final AutoCommandGroup m_autoCommand = new AutoCommandGroup();

  public int XBOX_R_XAXIS = 4;
  public int XBOX_R_YAXIS = 5;
  public int XBOX_L_XAXIS = 0;
  public int XBOX_L_YAXIS = 1;
  public int XBOX_L_Trigger = 2;
  public int XBOX_R_Trigger = 3;

  // Driver 1
  public XboxController xbox0 = new XboxController(0);
  public JoystickButton driver1ButtonA = new JoystickButton(xbox0, 1);
  public JoystickButton driver1ButtonX = new JoystickButton(xbox0, 3);
  public JoystickButton driver1ButtonY = new JoystickButton(xbox0, 4);
  public JoystickButton driver1ButtonB = new JoystickButton(xbox0, 2);
  public JoystickButton driver1LBumper = new JoystickButton(xbox0, 5);
  public JoystickButton driver1RBumper = new JoystickButton(xbox0, 6);
  public JoystickButton driver1Buttonback = new JoystickButton(xbox0, 7);
  public JoystickButton driver1Buttonstart = new JoystickButton(xbox0, 8);

  // Driver 2
  public XboxController xbox1 = new XboxController(1);
  public JoystickButton driver2ButtonA = new JoystickButton(xbox1, 1);
  public JoystickButton driver2ButtonX = new JoystickButton(xbox1, 3);
  public JoystickButton driver2ButtonY = new JoystickButton(xbox1, 4);
  public JoystickButton driver2ButtonB = new JoystickButton(xbox1, 2);
  public JoystickButton driver2RBumper = new JoystickButton(xbox1, 6);
  public JoystickButton driver2LBumper = new JoystickButton(xbox1, 5);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */

  private void configureButtonBindings() {
    // driver 1
    driver1RBumper.whenPressed(new Turnoff());
    driver1ButtonY.whileHeld(new index());
    driver1ButtonX.whenPressed(new countBalls());

    // driver 2 
    driver2ButtonA.whenPressed(new shoot());
    driver2ButtonX.whenPressed(new ShooterIdle());
    driver2ButtonB.whenPressed(new AutoAim());
    driver2LBumper.whenPressed(new RotationControl());
    driver2RBumper.whenPressed(new PositionControl());
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
