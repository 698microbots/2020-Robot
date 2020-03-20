/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Wof extends SubsystemBase {
  public static VictorSP wof = new VictorSP(0);
  // Var for fms
  String gameData;
  /**
   * Creates a new Wof.
   */
  public Wof() {

  }
  public void setSpeed(double speed) {
    wof.set(speed);
  }

  public String readFMS(){
    // Read from FMS for WOF
    gameData = DriverStation.getInstance().getGameSpecificMessage();
    if(gameData.length() > 0){
      switch(gameData.charAt(0)){
          case 'B' :
            return "Blue";
          case 'R' :
            return "Red";
          case 'Y' :
            return "Yellow";
          case 'G' :
            return "Green";
      }
    }
    return "";
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
