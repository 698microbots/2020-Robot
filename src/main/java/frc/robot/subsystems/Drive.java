/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.Drive.JoyStickDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
;

public class Drive extends SubsystemBase {
  private static Constants consts = new Constants();
  public static TalonFX FrontRight = new TalonFX(6);
  // public static TalonFX FrontLeft = new TalonFX(consts.FrontLeft);
  // public static TalonFX BackRight = new TalonFX(consts.BackRight);
  // public static TalonFX BackLeft = new TalonFX(consts.BackLeft);

  public void initDefaultCommand() {
    setDefaultCommand(new JoyStickDrive());
  }

  public Drive()   {
    
  }
  public void setRightSpeed(double speed) {
		if(speed<-1) speed =-1;
		if(speed>1) speed=1;
    //BackRight.set(TalonFXControlMode.PercentOutput,speed);
    FrontRight.set(TalonFXControlMode.PercentOutput,speed);
    SmartDashboard.putNumber("Speed: ", FrontRight.getSelectedSensorVelocity(0)* 600 / 4096);
  }
  public void setLeftSpeed(double speed) {
		if(speed<-1) speed =-1;
		if(speed>1) speed=1;
		//BackLeft.set(TalonFXControlMode.PercentOutput,speed);
		//FrontLeft.set(TalonFXControlMode.PercentOutput,speed);
  }
  public void DiveForward(int amount)
  {
    FrontRight.set(ControlMode.Position, amount);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
