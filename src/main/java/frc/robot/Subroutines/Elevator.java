package frc.robot.Subroutines;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkRelativeEncoder;
import edu.wpi.first.wpilibj.Timer;


import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elevator {
    SparkMax m_elevatorLeft = new SparkMax(5, MotorType.kBrushless); //states the existance of a sparkmax with a CANid of 5
    SparkMax m_elevatorRight = new SparkMax(6, MotorType.kBrushless); //states the existance of a sparkmax with a CANid of 6
    
    SparkMax[] CANIDs= {m_elevatorLeft, m_elevatorRight};

    // MotorControllerGroup m_winch = new MotorControllerGroup(m_winchLeft, m_winchRight);

    RelativeEncoder e_Encoder = m_elevatorLeft.getEncoder();
    DigitalInput LS_Elevator = new DigitalInput(2);
    double enc_Elevator_Zeroed;
    double enc_Elevator_Top;


    public void Initial(){
        SparkConfigure.ElevatorInit(m_elevatorLeft, m_elevatorRight);
        // m_elevatorLeft.setInverted(true);
        // SmartDashboard.putBoolean("Winch LS", LS_Elevator.get());
    }
    
    public void SetSpeed (double speed, boolean ControllerInput){
        double speedPercentage = 0.8; //range 0.0 to 1.0
        SmartDashboard.putBoolean("Winch LS", LS_Elevator.get());
        //SmartDashboard.putNumber("Winch Encoder", getEncoder());
        
        m_elevatorLeft.set(speed*speedPercentage);
            // m_elevatorLeft.set(0);
    }
    
    // public void TestMotor (double speed, int CAN_ID){
    //     //Used to test arm motors individually. Call on this function and supply speed and CAN_ID 5-6        
    //     if (CAN_ID <= 6 || CAN_ID >= 9){
    //         String S = Integer.toString(CAN_ID);
    //         System.out.println("CAN_ID #" + S + " is not assigned to an winch motor");
    //     }
    //     else{
    //         CANIDs[(CAN_ID-7)].set(speed);
    //     }
    // }

    // public void zeroWinch(Timer TimerSub){
    //     while(LS_Winch.get() == true && TimerSub.get() < 15){
    //         SetSpeed(-0.1, false);
    //         if (LS_Winch.get() == false)
    //         {
    //             enc_Winch_Zeroed = getEncoder();
    //             enc_Winch_Top = enc_Winch_Zeroed - 58.5;
    //             //SmartDashboard.putNumber("Winch Top", enc_Winch_Top);
    //             SetSpeed(0, false);
    //         }
    //     }
    // }

    public double getEncoder(){
        //Note: Up negative encoder, Down positive encoder
        return e_Encoder.getPosition();
    }
}
