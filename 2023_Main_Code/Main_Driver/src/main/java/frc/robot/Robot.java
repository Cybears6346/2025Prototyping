// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj.motorcontrol.*;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
/*   private final PWMSparkMax left1 = new PWMSparkMax(0);
  private final PWMSparkMax left2 = new PWMSparkMax(2);
  private final PWMSparkMax right1 = new PWMSparkMax(1);
  private final PWMSparkMax right2 = new PWMSparkMax(3); */
  private final XboxController m_controller = new XboxController(1);
  private final Timer m_timer = new Timer();
  
  private final MotorController m_frontLeft = new Victor(1);
  private final MotorController m_rearLeft = new Victor(0);
  private final MotorControllerGroup m_left = new MotorControllerGroup(m_frontLeft, m_rearLeft);
  private final MotorController m_frontRight = new Victor(2);
  private final MotorController m_rearRight = new Victor(3);
  private final MotorControllerGroup m_right = new MotorControllerGroup(m_frontRight, m_rearRight);
  private final DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right); 
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    m_left.setInverted(true);
  }

  /** This function is run once each time the robot enters autonomous mode. */
  @Override
  public void autonomousInit() {
    m_timer.reset();
    m_timer.start();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    // Drive for 2 seconds
    if (m_timer.get() < 2.0) {
      // Drive forwards half speed, make sure to turn input squaring off
      
    } else {
      // stop robot
    }
  }

  /** This function is called once each time the robot enters teleoperated mode. */
  @Override
  public void teleopInit() {
    
  }

  /** This function is called periodically during teleoperated mode. */
  @Override
  public void teleopPeriodic() {
    m_drive.arcadeDrive(-m_controller.getLeftY(), -m_controller.getRightX(), false);
    System.out.print(m_controller.getLeftY());
    System.out.print("hi");

  }

  /** This function is called once each time the robot enters test mode. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
