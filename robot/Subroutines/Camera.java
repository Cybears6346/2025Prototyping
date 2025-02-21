// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subroutines;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cscore.VideoSink;
import edu.wpi.first.cscore.VideoSource;

public class Camera {

    UsbCamera Camera0;
    UsbCamera Camera1;
    VideoSink CameraStream;

    public void Initial(){
        Camera0 = CameraServer.startAutomaticCapture("Front Camera", 0);
        Camera0.setConnectionStrategy(VideoSource.ConnectionStrategy.kKeepOpen);
        //Camera1 = CameraServer.startAutomaticCapture("Rear Camera", 1);
        //Camera1.setConnectionStrategy(VideoSource.ConnectionStrategy.kKeepOpen);
        
        CameraStream = CameraServer.addSwitchedCamera("Switched Camera");
        CameraStream.setSource(Camera0);
    }

    public void ToggleCamera(Boolean ControllerInput1, Boolean ControllerInput2){
        
        if (ControllerInput1 == true){
            CameraStream.setSource(Camera0);
        }
        if (ControllerInput2 == true){
            CameraStream.setSource(Camera1);
        }

    }






}
