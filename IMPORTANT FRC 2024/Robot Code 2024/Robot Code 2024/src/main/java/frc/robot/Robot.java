// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Subroutines.Arm;
import frc.robot.Subroutines.Autonomous;
import frc.robot.Subroutines.Camera;
import frc.robot.Subroutines.Driving;
import frc.robot.Subroutines.Pickup;
import frc.robot.Subroutines.Shooter;
import frc.robot.Subroutines.Winch;
import edu.wpi.first.wpilibj.XboxController;

public class Robot extends TimedRobot {
  XboxController DriverController = new XboxController(0); //Assigns a new Xbox controller to port 0
  XboxController ArmController = new XboxController(1); //Assigns a new Xbox controller to port 0

  Camera CamSub = new Camera(); //Allows us to call the Camera Subroutines in the Camera.Java class
  Driving DriveSub = new Driving(); //Allows us to call the Driving Subroutines in the Driving.Java class
  Arm ArmSub = new Arm(); //Allows us to call the Arm Subroutines in the Arm.Java class
  Pickup PickupSub = new Pickup(); //Allows us to call the Pickup Subroutines in the Pickup.Java class
  Shooter ShooterSub = new Shooter(); //Allows us to call the Shooter Subroutines in the Shooter.Java class
  Autonomous AutoSub = new Autonomous(); //Allows us to call the Autonomous Subroutines in the Autonomous.Java class
  Winch WinchSub = new Winch(); //Allows us to call the Autonomous Subroutines in the Autonomous.Java class

  String A_Selected;
  boolean AutoFinished = false;
  private static final String A_Stationary = "Stationary Score";
  private static final String A_Red_Source = "Red Source Score";
  private static final String A_Red_Center = "Red Center Score";
  private static final String A_Red_Amp = "Red Amp Score";
  private static final String A_Blue_Source = "Blue Source Score";
  private static final String A_Blue_Center = "Blue Center Score";
  private static final String A_Blue_Amp = "Blue Amp Score";
  String[] AutoNames= {A_Stationary, A_Red_Source, A_Red_Center, A_Red_Amp, A_Blue_Source, A_Blue_Center, A_Blue_Amp, ""};
  SendableChooser<String> Auto_Selector = new SendableChooser<>();

  @Override
  public void robotInit() { //This code runs when the robot powers on
    CamSub.Initial();//starts the camera streams from the roboRio
    AutoSub.Initial(Auto_Selector, AutoNames);
    DriveSub.Initial(); //inverts the rear right motor and other setup for the driving controls
    ArmSub.Initial(); //inverts the left arm motor and other setup for the arm controls
    ShooterSub.Initial();
    PickupSub.Initial();
    WinchSub.Initial();
    SmartDashboard.putString("Auto Command", "RobotInit");

  }
  @Override
  public void robotPeriodic(){
    //SmartDashboard.putNumber("Wheel Encoder",DriveSub.getEncoder());
  }

  @Override
  public void autonomousInit(){
    //This code starts the autonomous program. 
    //At the beginning of every autonomous, the winch and arm will move to zero on their limit switches.
    stopAll();
    getSelected();
    SmartDashboard.putString("Auto Command", "zeroWinch");
    WinchSub.zeroWinch();
    SmartDashboard.putString("Auto Command", "zeroArm");
    ArmSub.zeroArm();
    RunAuto();
    AutoFinished = true;
  }

  @Override
  public void teleopInit(){
    AutoFinished = false;
    SmartDashboard.putString("Auto Command", "AutoReset");
    stopAll();
  }

  @Override
  public void teleopPeriodic() {
    //Manual Drive Code: Controlled with Left Y and Right X on the Driver Controller
    DriveSub.ManualDrive(DriverController.getLeftY(), DriverController.getRightX(),true);
    
    //Winch Code: Controlled with the Left and Right Triggers on the Driver Controller
    WinchSub.SetSpeed(DriverController.getLeftTriggerAxis() - DriverController.getRightTriggerAxis(), DriverController.getAButton());

    //Manual Arm Code: Controlled by Left Y on the Arm Controller
    //Minimum speed must always be 0.05 to prevent arm from dropping
    if(Math.abs(ArmController.getLeftY())>0.05){ArmSub.SetSpeed(ArmController.getLeftY());}
    else{ArmSub.SetSpeed(0.05);}
    
    //Manual Pickup Code: Controlled by Left and Right Triggers on the Arm Controller
    PickupSub.SetSpeed(ArmController.getLeftTriggerAxis() - ArmController.getRightTriggerAxis());

    //Manual Shooter Code: Controlled by B Button on the Arm Controller
    ShooterSub.SetSpeed(1, ArmController.getBButton());
    
    //Preset Shooter Code: Controlled by A Button on the Arm Controller
    if(ArmController.getAButtonPressed()){
      AutoSub.Preset_Shoot(ShooterSub, PickupSub);
    }
    
    //Assign Shoot & Amp arm positions: Controlled by the Start and Back button on the Arm Controller
    ArmSub.setShootPosition(ArmController.getStartButtonPressed());
    ArmSub.setAmpPosition(ArmController.getBackButtonPressed());

    /*
    //Test Individual Motor Code: Controlled by Y button on the Driver Controller
    if (DriverController.getYButton() ==  true){
      TestMotor(0); //Change TestMotor(CAN_ID) to desired motor Range: 1 to 13
    }
    */
  }

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  public void RunAuto(){
    if (AutoFinished == false){
        switch (A_Selected) {
          //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
          case A_Stationary:
          AutoSub.Auto_StationaryScore(DriveSub, ArmSub, PickupSub, ShooterSub, 0);
          
          break;

          case A_Red_Source:
          AutoSub.Auto_Source(DriveSub, ArmSub, PickupSub, ShooterSub, "RED");
          break;

          case A_Red_Center:
          AutoSub.Auto_Center(DriveSub, ArmSub, PickupSub, ShooterSub, "RED");
          break;

          case A_Red_Amp:
          AutoSub.Auto_Amp(DriveSub, ArmSub, PickupSub, ShooterSub, "RED");
          break;

          case A_Blue_Source:
          AutoSub.Auto_Source(DriveSub, ArmSub, PickupSub, ShooterSub, "BLUE");
          break;

          case A_Blue_Center:
          AutoSub.Auto_Center(DriveSub, ArmSub, PickupSub, ShooterSub, "BLUE");
          break;

          case A_Blue_Amp:
          AutoSub.Auto_Amp(DriveSub, ArmSub, PickupSub, ShooterSub, "BLUE");
          break;

          default:
          break;
        }
    }
  }

  public void getSelected(){
    A_Selected = Auto_Selector.getSelected();
    SmartDashboard.putString("Auto Selected", A_Selected);
  }

  public void stopAll(){
    DriveSub.ManualDrive(0, 0, true);
    ArmSub.SetSpeed(0.05);
    PickupSub.SetSpeed(0);
    ShooterSub.SetSpeed(0, true);

  }

  public void TestMotor(int CAN_ID){
      if (DriverController.getYButton() == true){
        if(1 <= CAN_ID && CAN_ID <= 4){
          DriveSub.TestMotor(0.1, CAN_ID);
        }
        
        if(5 <= CAN_ID && CAN_ID <= 6){
        ArmSub.TestMotor(0.1, CAN_ID);
        }

        if(7 <= CAN_ID && CAN_ID <= 8){
        WinchSub.TestMotor(0.1, CAN_ID);
        }

        if(CAN_ID == 9){
        PickupSub.TestMotor(0.1, CAN_ID);
        }

        if(10 <= CAN_ID && CAN_ID <= 13){
        ShooterSub.TestMotor(0.1, CAN_ID);
        }
   
      }
      else{
        if(1 <= CAN_ID && CAN_ID <= 4){
          DriveSub.TestMotor(0, CAN_ID);
        }
        
        if(5 <= CAN_ID && CAN_ID <= 6){
        ArmSub.TestMotor(0, CAN_ID);
        }

        if(7 <= CAN_ID && CAN_ID <= 8){
        WinchSub.TestMotor(0, CAN_ID);
        }

        if(CAN_ID == 9){
        PickupSub.TestMotor(0, CAN_ID);
        }

        if(10 <= CAN_ID && CAN_ID <= 13){
        ShooterSub.TestMotor(0, CAN_ID);
        }
   
      }
    }
}