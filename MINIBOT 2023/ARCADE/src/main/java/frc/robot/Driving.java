// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

//These libraries are neccessary for the motors to run. These are can bus motors, not pwm.
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxRelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.SparkMaxAbsoluteEncoder;


/** Add your docs here. */
public class Driving {

    CANSparkMax m_rearLeft = new CANSparkMax(1, MotorType.kBrushless); //states the existance of a sparkmax with a CANid of 1
    CANSparkMax m_rearRight = new CANSparkMax(3, MotorType.kBrushless); //states the existance of a sparkmax with a CANid of 3
    CANSparkMax m_frontLeft = new CANSparkMax(2, MotorType.kBrushless); //states the existance of a sparkmax with a CANid of 2
    CANSparkMax m_frontRight = new CANSparkMax(4, MotorType.kBrushless); //states the existance of a sparkmax with a CANid of 4
    
    MotorControllerGroup m_Left = new MotorControllerGroup(m_frontLeft, m_rearLeft);
    MotorControllerGroup m_Right = new MotorControllerGroup(m_frontRight, m_rearRight);
    
    DifferentialDrive m_drive = new DifferentialDrive(m_Left, m_Right);

    public void DrivingInitial (){
        m_rearRight.setInverted(true);
    }

    public void ManualDrive (double speed, double rotation, boolean square){
        double speedPercentage = 1.0; //range 0.0 to 1.0
        double rotationPercentage = 0.7; //range 0.0 to 1.0
        m_drive.arcadeDrive(-speed*speedPercentage, -rotation*rotationPercentage, square);
    }

    public void SetDrive (double speed, double rotation, double steps){
        double enc_WheelCurrent = 0;
        double enc_WheelTarget = enc_WheelCurrent + steps;
        //Future code: Give move a number of steps based using the encoder. If or While loop probably
        //Look at SparkMax documentation to read relative encoder values
        m_drive.arcadeDrive(-speed, -rotation); //Move
        m_drive.arcadeDrive(0, 0); //Stop
    }
}
