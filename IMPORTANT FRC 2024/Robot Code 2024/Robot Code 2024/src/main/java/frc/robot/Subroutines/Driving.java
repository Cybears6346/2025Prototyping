// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subroutines;

//These libraries are neccessary for the motors to run. These are can bus motors, not pwm.
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkRelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

public class Driving {

    CANSparkMax m_frontRight = new CANSparkMax(1, MotorType.kBrushless); //states the existance of a sparkmax with a CANid of 4
    CANSparkMax m_rearRight = new CANSparkMax(2, MotorType.kBrushless); //states the existance of a sparkmax with a CANid of 3
    CANSparkMax m_rearLeft = new CANSparkMax(3, MotorType.kBrushless); //states the existance of a sparkmax with a CANid of 1
    CANSparkMax m_frontLeft = new CANSparkMax(4, MotorType.kBrushless); //states the existance of a sparkmax with a CANid of 2
    
    CANSparkMax[] CANIDs= {m_frontRight, m_rearRight, m_rearLeft, m_frontLeft};
    
    MotorControllerGroup m_Left = new MotorControllerGroup(m_frontLeft, m_rearLeft);
    MotorControllerGroup m_Right = new MotorControllerGroup(m_frontRight, m_rearRight);

    DifferentialDrive m_drive = new DifferentialDrive(m_Left, m_Right);

    RelativeEncoder d_Encoder_Left = m_frontLeft.getEncoder(SparkRelativeEncoder.Type.kHallSensor, 42);

    public void Initial (){
        m_frontRight.setInverted(true);
    }

    public void ManualDrive (double speed, double rotation, boolean square){
        double speedPercentage = 0.7; //range 0.0 to 1.0
        double rotationPercentage = 0.7; //range 0.0 to 1.0
        m_drive.arcadeDrive(-speed*speedPercentage, -rotation*rotationPercentage, square);
    }

    public void AutoDrive (double speed, double rotation){
        double speedPercentage = 0.7; //range 0.0 to 1.0
        double rotationPercentage = 0.7; //range 0.0 to 1.0
        m_drive.arcadeDrive(-speed*speedPercentage, -rotation*rotationPercentage, false);
    }

    public double Feet2Steps (double Distance_Feet){
        double StepsPerRev = 8.475;
        double enc_WheelCurrent = getEncoder();
        double enc_WheelTarget = enc_WheelCurrent + (Distance_Feet*12*(1/(6*(Math.PI)))*StepsPerRev);
        return enc_WheelTarget;
    }

    public void TestMotor (double speed, int CAN_ID){
        //Used to test drive motors individually. Call on this function and supply speed and CAN_ID 1 - 4          
        if (CAN_ID <= 0 || CAN_ID >= CANIDs.length){
            String S = Integer.toString(CAN_ID);
            System.out.println("CAN_ID #" + S + " is not assigned to a drive motor");
        }
        else{
            CANIDs[(CAN_ID-1)].set(speed);
        }
    }

    public double getEncoder(){
        return d_Encoder_Left.getPosition();
    }
}
