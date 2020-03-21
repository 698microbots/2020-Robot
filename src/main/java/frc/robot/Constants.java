/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    // CAN IDs
    public int 	shooter  = 1;
    public int 	FrontRight = 4;
    public int FrontLeft = 3;
    public int 	BackRight = 5;
    public int BackLeft = 2;
    public int Turret = 7;
    public int intake = 6;
    public int serializer2 = 8;
    public int serializer1 = 9;
    public int indexer = 10;  
    
    // Photoe DIO IDs
    public int photoe1 = 0;
    public int photoe2 = 1;

    // Shooter PID Configurations
    public double shooterkP = 0.9;
    public double shooterkF = 0.0;
    public double shooterkI = 0.0001;
    public double shooterkD = 0.0;

    // Turret PID Configurations
    public double turretkP = 0.1;
    public double turretkI = 0.005;
    public double turretkD = 0.0;
    public double turretkF = 0.0;
    
    //Initial Pose
    public double InitialX = 0;
    public double InitialY = 0;

    //Trajectory constants
    public int kMaxSpeedMetersPerSecond = 5;
    public int kMaxAccelerationMetersPerSecondSquared = 3;
    public double ksVolts = 0.0;
    public double kvVoltSecondsPerMeter = 0.0;
    public double kaVoltSecondsSquaredPerMeter = 0.0;
    public DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(.6858);
    public double kRamseteB = 2;
    public double kRamseteZeta = 0.7;
    public double kPDriveVel = 0.0;
}
