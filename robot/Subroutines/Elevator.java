
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
    double speedPercentage = 0.8; //range 0.0 to 1.0

    // Elevator Submethod Variables and Arrays, doubles are distances from one place to another
    double bottomToL1 = 17.875 - 6.25;
    double l1ToL2 = 31.75 - 6.25;
    double l2ToL3 = 47.625 - 6.25;
    double l3ToL4 = 71.875 - 6.25;
    double l4ToTop = 100 - 6.25;

    double[] levels = {bottomToL1, l1ToL2, l2ToL3, l3ToL4, l4ToTop};

    double revolutionsToInches = 5;
    int StagingCount = 0; // Will range from 0 to 4, in accordance with 'levels' array (levels.length - 1)

    public void Initial()
    {
        SparkConfigure.ElevatorInit(m_elevatorLeft, m_elevatorRight);
        // m_elevatorLeft.setInverted(true);
        // SmartDashboard.putBoolean("Winch LS", LS_Elevator.get());
    }
    
    public void SetSpeed (double speed, boolean ControllerInput)
    {
        // SmartDashboard.putBoolean("Winch LS", LS_Elevator.get());
        // SmartDashboard.putNumber("Winch Encoder", getEncoder());
        
        m_elevatorLeft.set(speed * speedPercentage);
        System.out.println(e_Encoder);
            // m_elevatorLeft.set(0);
    }
    
    public void StageUp(double speed)
    {
        if (StagingCount != 4)
        {
            while (getEncoder() < levels[StagingCount])
            {
                m_elevatorLeft.set(-speed * speedPercentage); // Due to positive encoder readings signaling down
            }
            
            StagingCount++;
        } else
        {
            System.out.println("Maximum level reached");
        }
    }

    public void StageDown(double speed)
    {
        if (StagingCount != 0)
        {
            while (getEncoder() > levels[StagingCount])
            {
                m_elevatorLeft.set(speed * speedPercentage); // Due to negative encoder readings signaling up
            }
            StagingCount--;
        } else
        {
            System.out.println("Minimum level reached");
        }
    }

    // public void CalibrateUpward(double speed)
    // {
    //     while (getEncoder() < 100) // MUST BE CHANGED IN FUTURE TO REFLECT TRUE CONVERSION FACTOR
    //     {
    //         m_elevatorLeft.set(-speed * speedPercentage);
    //     }
    //     StagingCount = 4;
    // }

    // public void CalibrateDownward(double speed)
    // {
    //     while (getEncoder() > 0)
    //     {
    //         m_elevatorLeft.set(speed * speedPercentage);
    //     }
    //     StagingCount = 0;
    // }

    // public void LevelAlign() // In case we want free control with joystick
    // {
    //     if ()
    //     {

    //     }
    // }

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
