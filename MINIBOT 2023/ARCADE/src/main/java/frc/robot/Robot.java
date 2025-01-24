// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import java.lang.Math.*;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Tracer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.*;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DutyCycleEncoder;

//These 3 libraries are neccessary for the motors to run. These are can bus motors, not pwm.
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxRelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.SparkMaxAbsoluteEncoder;

import edu.wpi.first.math.trajectory.*;
/**
 * This is a demo program showing the use of the DifferentialDrive class. Runs the motors with split
 * arcade steering and an Xbox controller.
 */
public class Robot extends TimedRobot {
  XboxController DriverController = new XboxController(0); //Assigns a new Xbox controller to port 0

  Driving DriveSub = new Driving(); //Allows us to call the Driving Subroutines in the Driving.Java class
  Autonomous AutoSub = new Autonomous(); //Allows us to call the Autonomous Subroutines in the Autonomous.Java class

  
  @Override
  public void robotInit() { //This code runs when the robot powers on
    
    DriveSub.DrivingInitial(); //inverts the rear right motor and other setup for the driving controls
  }

  @Override
  public void teleopPeriodic() {
    // Drive with split arcade drive.
    // That means that the Y axis of the left stick moves forward
    // and backward, and the X of the right stick turns left and right.
    //NOTE: Xbox controllers -Y is up and +Y is down
    //m_drive.arcadeDrive(-m_driverController.getLeftY(), -m_driverController.getRightX()*0.7, true);

    DriveSub.ManualDrive(DriverController.getLeftY(), DriverController.getRightX(),true);
  }
}
