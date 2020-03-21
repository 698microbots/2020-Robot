/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SPI;
//import subsystems
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;
import frc.robot.subsystems.Vision;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Intake;

//import commands
import frc.robot.commands.Shooter.*;

import edu.wpi.first.wpilibj.XboxController;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.commands.Drive.AutoDrive;
//import commands to be default
import frc.robot.commands.Drive.JoyStickDrive;
import frc.robot.commands.Intake.MoveBalls;
import frc.robot.commands.Shooter.Turnoff;
import frc.robot.commands.Turret.TurretJoyStick;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private AutoDrive autoDrive = new AutoDrive();
  public static RobotContainer oi;

  public static PowerDistributionPanel pdp = new PowerDistributionPanel();

  // Create instances of subsystems
  public static Shooter shooter = new Shooter();
  public static Vision vision = new Vision();
  public static Drive drive = new Drive();
  public static Turret turret = new Turret();
  public static Intake intake = new Intake();

  // Create Limelight
  public static NetworkTable limelight = NetworkTableInstance.getDefault().getTable("limelight");
  public static NetworkTableEntry ledMode = limelight.getEntry("ledMode");

  public static AHRS navx = new AHRS(SPI.Port.kMXP);

  // Var for fms
    String gameData;
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    oi = new RobotContainer();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = oi.getAutonomousCommand();

    SmartDashboard.putBoolean("Autonomous: ", true);
    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    autoDrive.execute();
    drive.getDriveValues();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }

    // Set Default Commands for subsystems
    drive.setDefaultCommand(new JoyStickDrive());
    //turret.setDefaultCommand(new TurretJoyStick());
    shooter.setDefaultCommand(new ShooterIdle());
    intake.setDefaultCommand(new MoveBalls());
    ledMode.setNumber(1);
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    // Needs to be removed
    shooter.getVelocity();
    turret.getPosition();
    turret.getVelocity();

    // Read from FMS for WOF
    gameData = DriverStation.getInstance().getGameSpecificMessage();
    if(gameData.length() > 0){
      switch(gameData.charAt(0)){
          case 'B' :
          //blue code
          SmartDashboard.putString("wheel color", "Blue");
          break;
          case 'R' :
          //red code
          SmartDashboard.putString("wheel color", "Red");
          break;
          case 'Y' :
          //yellow code
          SmartDashboard.putString("wheel color", "Yellow");
          break;
          case 'G' :
          //green code
          SmartDashboard.putString("wheel color", "Green");
          break;
      }
    }

    // TODO: Needs to be put into separate file
    // Testing Purposes
    // SmartDashboard.putNumber("Roll: ", navx.getRoll());
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
