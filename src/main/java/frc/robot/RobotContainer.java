/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.shoot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.*;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private final shoot m_autoCommand = new shoot();

  public XboxController	xbox0 	= new XboxController(0);
  public int XBOX_R_XAXIS = 4;
	public int XBOX_R_YAXIS = 5;
	public int XBOX_L_XAXIS = 0;
	public int XBOX_L_YAXIS = 1;
  public JoystickButton driverButtonA = new JoystickButton(xbox0, 1);
  public JoystickButton driverButtonX = new JoystickButton(xbox0, 3);
  public JoystickButton driverButtonY = new JoystickButton(xbox0, 4);
  public JoystickButton driverButtonB = new JoystickButton(xbox0, 2);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  
  private void configureButtonBindings() {
    driverButtonA.whenPressed(new shoot());
    // driverButtonB.whenPressed(new shoot(1.0));
    // driverButtonY.whenPressed(new shoot(0.2));
    driverButtonX.whenPressed(new Turnoff());
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
