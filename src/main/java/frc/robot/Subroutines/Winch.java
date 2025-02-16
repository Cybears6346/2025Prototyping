// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subroutines;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkRelativeEncoder;
import edu.wpi.first.wpilibj.Timer;


import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/** Add your docs here. */
public class Winch {
    SparkMax m_winchLeft = new SparkMax(7, MotorType.kBrushless); //states the existance of a sparkmax with a CANid of 5
    SparkMax m_winchRight = new SparkMax(8, MotorType.kBrushless); //states the existance of a sparkmax with a CANid of 6
    
    SparkMax[] CANIDs= {m_winchLeft, m_winchRight};

    // MotorControllerGroup m_winch = new MotorControllerGroup(m_winchLeft, m_winchRight);

    RelativeEncoder w_Encoder = m_winchRight.getEncoder();
    DigitalInput LS_Winch = new DigitalInput(2);
    double enc_Winch_Zeroed;
    double enc_Winch_Top;


    public void Initial(){
        SparkConfigure.winchInit(m_winchLeft, m_winchRight);
        // m_winchLeft.setInverted(true);
        SmartDashboard.putBoolean("Winch LS", LS_Winch.get());
    }
    
    public void SetSpeed (double speed, boolean ControllerInput){
        double speedPercentage = 0.8; //range 0.0 to 1.0
        SmartDashboard.putBoolean("Winch LS", LS_Winch.get());
        //SmartDashboard.putNumber("Winch Encoder", getEncoder());
        if (((LS_Winch.get() == true) && speed < 0) || (getEncoder() > enc_Winch_Top && speed > 0) || (ControllerInput == true)){
            m_winchLeft.set(-speed*speedPercentage);
        }
        else{
            m_winchLeft.set(0);
        }
    }
    
    public void TestMotor (double speed, int CAN_ID){
        //Used to test arm motors individually. Call on this function and supply speed and CAN_ID 5-6        
        if (CAN_ID <= 6 || CAN_ID >= 9){
            String S = Integer.toString(CAN_ID);
            System.out.println("CAN_ID #" + S + " is not assigned to an winch motor");
        }
        else{
            CANIDs[(CAN_ID-7)].set(speed);
        }
    }

    public void zeroWinch(Timer TimerSub){
        while(LS_Winch.get() == true && TimerSub.get() < 15){
            SetSpeed(-0.1, false);
            if (LS_Winch.get() == false)
            {
                enc_Winch_Zeroed = getEncoder();
                enc_Winch_Top = enc_Winch_Zeroed - 58.5;
                //SmartDashboard.putNumber("Winch Top", enc_Winch_Top);
                SetSpeed(0, false);
            }
        }
    }

    public double getEncoder(){
        //Note: Up negative encoder, Down positive encoder
        return w_Encoder.getPosition();
    }





}
