// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subroutines;

//These libraries are neccessary for the motors to run. These are can bus motors, not pwm.
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkRelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Timer;

public class Arm {

    CANSparkMax m_armLeft = new CANSparkMax(5, MotorType.kBrushless); //states the existance of a sparkmax with a CANid of 5
    CANSparkMax m_armRight = new CANSparkMax(6, MotorType.kBrushless); //states the existance of a sparkmax with a CANid of 6
    
    CANSparkMax[] CANIDs= {m_armLeft, m_armRight};

    MotorControllerGroup m_arm = new MotorControllerGroup(m_armLeft, m_armRight);

    RelativeEncoder a_Encoder = m_armRight.getEncoder(SparkRelativeEncoder.Type.kHallSensor, 42);
    DigitalInput LS_Front = new DigitalInput(0);
    DigitalInput LS_Rear = new DigitalInput(1);

    double enc_Arm_Zeroed;
    double enc_ShootPosition = -29;
    double enc_AmpPosition = -22.5;
    
    public void Initial (){
        m_armLeft.setInverted(true);
        SmartDashboard.putBoolean("Front LS", LS_Front.get());
        SmartDashboard.putBoolean("Rear LS", LS_Rear.get());
    }

    public void SetSpeed (double speed){
        SmartDashboard.putNumber("Shoot Position", enc_ShootPosition);
        SmartDashboard.putBoolean("Front LS", LS_Front.get());
        SmartDashboard.putBoolean("Rear LS", LS_Rear.get());
        SmartDashboard.putBoolean("Arm Ready", isReady(enc_ShootPosition));
        SmartDashboard.putBoolean("Amp Ready", isReady(enc_AmpPosition));
        if((LS_Front.get() == true && speed < 0) || (LS_Rear.get() == true && speed > 0 )){    
            double speedPercentage = 0.3; //range 0.0 to 1.0
            m_arm.set(-speed*speedPercentage);
            SmartDashboard.putNumber("Arm Encoder", getEncoder());
        }
        else{
            m_arm.set(-0.05);
            SmartDashboard.putNumber("Arm Encoder", getEncoder());
        }
    }

    public void SetSpeedAuto (double speed){
        SmartDashboard.putNumber("Shoot Position", enc_ShootPosition);
        SmartDashboard.putBoolean("Front LS", LS_Front.get());
        SmartDashboard.putBoolean("Rear LS", LS_Rear.get());
        SmartDashboard.putBoolean("Arm Ready", isReady(enc_ShootPosition));
        SmartDashboard.putBoolean("Amp Ready", isReady(enc_AmpPosition));
        if((LS_Front.get() == true && speed < 0) || (LS_Rear.get() == true && speed > 0 )){    
            double speedPercentage = 0.5; //range 0.0 to 1.0
            m_arm.set(-speed*speedPercentage);
            SmartDashboard.putNumber("Arm Encoder", getEncoder());
        }
        else{
            m_arm.set(-0.05);
            SmartDashboard.putNumber("Arm Encoder", getEncoder());
        }
    }

    public void TestMotor (double speed, int CAN_ID){
        //Used to test arm motors individually. Call on this function and supply speed and CAN_ID 5-6    
        if (CAN_ID <= 4 || CAN_ID >= 7){
            String S = Integer.toString(CAN_ID);
            System.out.println("CAN_ID #" + S + " is not assigned to an arm motor");
        }
        else{
            CANIDs[(CAN_ID-5)].set(speed);
        }
    }

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void zeroArm(Timer TimerSub){
        //Use these Offset Values to tune the Auto_StationaryScore() in the center speaker position 
        //and the Shuffleboard indicator for Amp Scoring
        double ShootPosition_Offset = 0; //Positive = Lower Arm, Negative = Raise Arm (+/- 10)
        double AmpPosition_Offset = 0; //Positive = Lower Arm, Negative = Raise Arm (+/- 10)
        while(LS_Rear.get() == true && TimerSub.get() < 15){
            SetSpeed(0.6);
            if (LS_Rear.get() == false)
            {
                enc_Arm_Zeroed = getEncoder();
                enc_ShootPosition = enc_Arm_Zeroed + 27 + ShootPosition_Offset;
                enc_AmpPosition = enc_Arm_Zeroed + 24.5 + AmpPosition_Offset;
                SmartDashboard.putNumber("Shoot Position", enc_ShootPosition);
                SmartDashboard.putNumber("Amp Position", enc_AmpPosition);
                SetSpeed(0.05);
            }
        }
    }
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public double getZero(){
        return enc_Arm_Zeroed;
    }

    public double getShootPosition(){
        return enc_ShootPosition;
    }

    public double getAmpPosition(){
        return enc_AmpPosition;
    }

    public void setShootPosition(boolean ControllerInput){
        if(ControllerInput == true){
            enc_ShootPosition = getEncoder();
            SmartDashboard.putNumber("Shoot Position", enc_ShootPosition);
        }
    }

    public void setAmpPosition(boolean ControllerInput){
        if(ControllerInput == true){
            enc_AmpPosition = getEncoder();
            SmartDashboard.putNumber("Amp Position", enc_AmpPosition);
        }
    }

    public boolean isReady(double Position){
        boolean ready;
        if(getEncoder() >= (Position - 1) && getEncoder() <= (Position + 1)) {ready = true;}
        else{ready = false;}
        return ready;
    }

    public double getEncoder(){
        //encoder positive move down, negative move up
        return a_Encoder.getPosition();
    }
}