// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

// package frc.robot;
// import java.lang.Math.*;

// import edu.wpi.first.wpilibj.TimedRobot;
// import edu.wpi.first.wpilibj.Tracer;
// import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj.motorcontrol.MotorController;
// import edu.wpi.first.wpilibj.motorcontrol.*;
// import edu.wpi.first.wpilibj.XboxController;
// import edu.wpi.first.wpilibj.I2C.Port;
// import edu.wpi.first.wpilibj.drive.DifferentialDrive;
// import edu.wpi.first.wpilibj.Encoder;
// import edu.wpi.first.wpilibj.DigitalInput;
// import edu.wpi.first.wpilibj.DutyCycleEncoder;

// //These 3 libraries are neccessary for the motors to run. These are can bus motors, not pwm.
// import com.revrobotics.CANSparkMax;
// import com.revrobotics.RelativeEncoder;
// import com.revrobotics.SparkMaxRelativeEncoder;
// import com.revrobotics.CANSparkMaxLowLevel.MotorType;
// import com.revrobotics.AbsoluteEncoder;
// import com.revrobotics.SparkMaxAbsoluteEncoder;

// import edu.wpi.first.math.trajectory.*;
// /**
//  * This is a demo program showing the use of the DifferentialDrive class. Runs the motors with split
//  * arcade steering and an Xbox controller.
//  */
// public class Robot extends TimedRobot {
//   public static double kDefaultSpeed;

// XboxController DriverController = new XboxController(0); //Assigns a new Xbox controller to port 0

//   Driving DriveSub = new Driving(); //Allows us to call the Driving Subroutines in the Driving.Java class
//   Autonomous AutoSub = new Autonomous(); //Allows us to call the Autonomous Subroutines in the Autonomous.Java class

  
//   @Override
//   public void robotInit() { //This code runs when the robot powers on
    
//     //DriveSub.DrivingInitial(); //inverts the rear right motor and other setup for the driving controls
//   }

//   @Override
//   public void teleopPeriodic() {
//     // Drive with split arcade drive.
//     // That means that the Y axis of the left stick moves forward
//     // and backward, and the X of the right stick turns left and right.
//     //NOTE: Xbox controllers -Y is up and +Y is down
//     //m_drive.arcadeDrive(-m_driverController.getLeftY(), -m_driverController.getRightX()*0.7, true);


//     DriveSub.ManualDrive(DriverController.getRightX(), DriverController.getLeftY(),true);
//     //DriveSub.ManualDrive(DriverController.getRightY(), DriverController.getLeftX(),true);
//     // DriveSub.ManualDrive(DriverController.getLeftY(), DriverController.getRightX(),true);
//   }
// }

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.XboxController;

public class Robot extends TimedRobot {
  public static double kDefaultSpeed = 1.0;

  XboxController DriverController = new XboxController(0); //Assigns a new Xbox controller to port 0
  XboxController ArmController = new XboxController(1); //Assigns a new Xbox controller to port 0

  Driving DriveSub = new Driving(); //Allows us to call the Driving Subroutines in the Driving.Java class
  Autonomous AutoSub = new Autonomous(); //Allows us to call the Autonomous Subroutines in the Autonomous.Java class

  String A_Selected;
  boolean AutoFinished = false;
  private static final String A_Stationary = "Stationary Score";
  private static final String A_Red_Source = "Red Source Score";
  private static final String A_Red_Center = "Center Score";
  private static final String A_Red_Amp = "Red Amp Score";
  private static final String A_Blue_Source = "Blue Source Score";
  private static final String A_Blue_Center = "Fast Center Score";
  private static final String A_Blue_Amp = "Blue Amp Score";
  private static final String A_Nothing = "Nothing";
  String[] AutoNames= {A_Stationary, A_Red_Source, A_Red_Center, A_Red_Amp, A_Blue_Source, A_Blue_Center, A_Blue_Amp, A_Nothing, ""};
  SendableChooser<String> Auto_Selector = new SendableChooser<>();

  @Override
  public void robotInit() { //This code runs when the robot powers on
    DriveSub.Initial(); //inverts the rear right motor and other setup for the driving controls
    SmartDashboard.putString("Auto Command", "RobotInit");
    SmartDashboard.putNumber("Drive Speed %", kDefaultSpeed);

  }
  @Override
  public void robotPeriodic(){
    //SmartDashboard.putNumber("Wheel Encoder",DriveSub.getEncoder());
  }

  @Override
  public void autonomousInit(){
    //This code starts the autonomous program. 
    //At the beginning of every autonomous, the winch and arm will move to zero on their limit switches.
    SmartDashboard.putString("Auto Command", "zeroWinch");
    SmartDashboard.putString("Auto Command", "zeroArm");
    AutoFinished = true;
  }

 
  @Override
  public void teleopInit(){
    AutoFinished = false;
    SmartDashboard.putString("Auto Command", "AutoReset");
  }

  @Override
  public void teleopPeriodic() {
    //Manual Drive Code: Controlled with Left Y and Right X thumb sticks on the Driver Controller
    DriveSub.ManualDrive(DriverController.getRightX(), DriverController.getLeftY(),true);
    // DriveSub.ManualDrive(DriverController.getLeftY(), DriverController.getRightX(),true);
    
    //Winch Code: Controlled with the Right Y thumb stick on the Arm Controller

    //Manual Arm Code: Controlled by Left Y on the Arm Controller
    //Minimum speed must always be 0.05 to prevent arm from dropping
    
    //Manual Pickup Code: Controlled by Left and Right Triggers on the Arm Controller

    //Manual Shooter Code: Controlled by B Button on the Arm Controller
    
    //Preset Shooter Code: Controlled by A Button on the Arm Controller
    
    //Assign Shoot & Amp arm positions: Controlled by the Start and Back button on the Arm Controller

    /*
    //Test Individual Motor Code: Controlled by Y button on the Driver Controller
    if (DriverController.getYButton() ==  true){
      TestMotor(0); //Change TestMotor(CAN_ID) to desired motor Range: 1 to 13
    }
    */
  }

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~




  public void TestMotor(int CAN_ID){
      if (DriverController.getYButton() == true){
        if(1 <= CAN_ID && CAN_ID <= 4){
          DriveSub.TestMotor(0.1, CAN_ID);
        }
        
      }
      else{
        if(1 <= CAN_ID && CAN_ID <= 4){
          DriveSub.TestMotor(0, CAN_ID);
        }
        
      }
    }
}
