// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subroutines;

//These libraries are neccessary for the motors to run. These are can bus motors, not pwm.
import com.revrobotics.spark.SparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkRelativeEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

public class Shooter {
    SparkMax m_shooterTopRight = new SparkMax(11, MotorType.kBrushless); //states the existance of a sparkmax with a CANid of 10
    SparkMax m_shooterBotRight = new SparkMax(10, MotorType.kBrushless); //states the existance of a sparkmax with a CANid of 11
    SparkMax m_shooterTopLeft = new SparkMax(13, MotorType.kBrushless); //states the existance of a sparkmax with a CANid of 12
    SparkMax m_shooterBotLeft = new SparkMax(12, MotorType.kBrushless); //states the existance of a sparkmax with a CANid of 13

    SparkMax[] CANIDs= {m_shooterBotRight, m_shooterTopRight, m_shooterBotLeft, m_shooterTopLeft};

    MotorControllerGroup m_shooterLeft = new MotorControllerGroup(m_shooterTopLeft, m_shooterBotLeft);
    MotorControllerGroup m_shooterRight = new MotorControllerGroup(m_shooterTopRight, m_shooterBotRight);
    MotorControllerGroup m_shooter = new MotorControllerGroup(m_shooterLeft, m_shooterRight);

    RelativeEncoder s_Encoder = m_shooterBotLeft.getEncoder();

    public void Initial (){
        m_shooterRight.setInverted(true);
    }

    public void SetSpeed (double speed, boolean ControllerButtonInput){
        //ControllerButtonInput set to the appropriate .get value. To just set the motor speed independant of the controller set to true in the parameters.
        double speedPercentage = 1.0; //range 0.0 to 1.0
        if (ControllerButtonInput == true){
            m_shooter.set(-speed*speedPercentage);
    }
        else{
            m_shooter.set(0);
        }
    }

    public void TestMotor (double speed, int CAN_ID){
        //Used to test arm motors individually. Call on this function and supply speed and CAN_ID 9-13      
        if (CAN_ID <= 9 || CAN_ID >= 14){
            String S = Integer.toString(CAN_ID);
            System.out.println("CAN_ID #" + S + " is not assigned to an shooter motor");
        }
        else{
            CANIDs[(CAN_ID-10)].set(speed);
        }
    }
    
    public double getEncoder(){
        //Note: Using Shooter decreases encoder value
        return s_Encoder.getPosition();
    }
}
