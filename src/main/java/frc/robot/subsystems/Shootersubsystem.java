/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.shoot;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.PowerDistributionPanel;

public class Shootersubsystem extends SubsystemBase {
  private static Constants consts = new Constants();
  //public static TalonFX shooter = new TalonFX(consts.shooter1);
  //public static TalonFX shooter2 = new TalonFX(consts.shooter2);
  public static VictorSP shooter = new VictorSP(consts.shooter1);
  public static VictorSP shooter1 = new VictorSP(consts.shooter2);
  public static PowerDistributionPanel pdp = new PowerDistributionPanel();  
  private static int CurrentCounter = 0;

  public void initDefaultCommand() {
  }

  public Shootersubsystem() {
  }
  public void run(double speed)
  {
    if(speed <= 0.05)
    {
      speed = 0;
    }
    
    if(pdp.getCurrent(3) < 60)
    {
      if(CurrentCounter > 0) CurrentCounter--;
      if(CurrentCounter == 0){
        shooter.set(speed);
        shooter1.set(speed);
      }
      SmartDashboard.putBoolean("Stop: ", false);
    }else{
      CurrentCounter++;
      if(CurrentCounter > 10)
      {
        CurrentCounter = 100;
        SmartDashboard.putBoolean("Stop: ", true);
        shooter.set(0.0);
        shooter1.set(0.0);
      }  
    }
    SmartDashboard.putNumber("Current Counter: ", CurrentCounter);
    //shooter.set(ControlMode.PercentOutput,speed);
    //shooter2.set(ControlMode.PercentOutput,speed);
    // shooter.set(speed);
    // shooter1.set(speed);
  }

  public void norun()
  {
    //shooter.set(ControlMode.Velocity,0);
    shooter.set(0);
    shooter1.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void getStuff()
  {
    //SmartDashboard.putNumber("Velocity: ", shooter.getSelectedSensorVelocity()/2048/0.1 * 60);

  }
}
