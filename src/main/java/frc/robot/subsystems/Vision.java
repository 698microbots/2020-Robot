/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.Shooter.shoot;
import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.Pixy2.LinkType;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;

public class Vision extends SubsystemBase {

    // PixyCam
    // Create instance for PixyCam
    private Pixy2 wof;
    // Check PixyCam state
    private int state;
    // Store PixyCam Info
    ArrayList <Block> blocks;
    // Array Color
    final String[] colors = {"", "Blue", "Green", "Red", "Yellow"}; 
    // Existence of camera
    private boolean isCamera;

    // Photoelectric Sensor
    private DigitalInput photoe;

    public void initDefaultCommand() {
    }

    public Vision() {
        // PixyCam var initialize
        isCamera = false;
        wof = Pixy2.createInstance(LinkType.SPI);
        state = -1;

        // Photoelectric var initialize
        photoe = new DigitalInput(0);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    public void countBalls()
    {
        SmartDashboard.putBoolean("Photoe", photoe.get());
    }

    public void readColor()
    {
        if(!isCamera){
            // Initialize PixyCam if no camera present
            state = wof.init(0);
        }
        isCamera = state >= 0;

        // run getBlocks with arguments to have the camera
        // 15 represents reading 4 signatures
        // 255 represents read max number of blocks 
        wof.getCCC().getBlocks(false, 15, 255);

        // get blocks
        blocks = wof.getCCC().getBlocks();

        if(blocks.size() > 0)
        {
            // x and y position of largest target
            double xCoord = blocks.get(0).getX();
            double yCoord = blocks.get(0).getY();

            int signature = blocks.get(0).getSignature();

            // String containing target info
            String data = blocks.get(0).toString();

            // Put to dashboard
            SmartDashboard.putBoolean("Present: ", true);
            SmartDashboard.putNumber("X Coordinate: ", xCoord);
            SmartDashboard.putNumber("Y Coordinate: ", yCoord);
            SmartDashboard.putString("Color: ", colors[signature]);
            SmartDashboard.putString("Data: ", data);
        }
        else
        {
            SmartDashboard.putBoolean("Present: ", false);
            // Check to see how many targets are present
            SmartDashboard.putNumber("Size: ", blocks.size());
        }
    }

}
