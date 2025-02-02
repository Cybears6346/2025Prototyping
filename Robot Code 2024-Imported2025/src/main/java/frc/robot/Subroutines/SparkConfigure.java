package frc.robot.Subroutines;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;

// import edu.wpi.first.wpilibj.TimedRobot;
// import edu.wpi.first.wpilibj.XboxController;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class SparkConfigure {

    public static void Drive_Initialize(SparkMax m_LeaderLeft, SparkMax m_LeaderRight, SparkMax m_FollowerLeft, SparkMax m_FollowerRight)
    {
    SparkMaxConfig globalLeaderConfig = new SparkMaxConfig();
    SparkMaxConfig rightLeaderConfig = new SparkMaxConfig();
    SparkMaxConfig leftFollowerConfig = new SparkMaxConfig();
    SparkMaxConfig rightFollowerConfig = new SparkMaxConfig();
        globalLeaderConfig
            .smartCurrentLimit(50)
            .idleMode(IdleMode.kBrake);

        // Apply the global config and invert since it is on the opposite side
        rightLeaderConfig
            .apply(globalLeaderConfig)
            .inverted(true);

        // Apply the global config and set the leader SPARK for follower mode
        leftFollowerConfig
            .apply(globalLeaderConfig)
            .follow(m_LeaderLeft)
            .inverted(true);

        // Apply the global config and set the leader SPARK for follower mode
        rightFollowerConfig
            .apply(globalLeaderConfig)
            .follow(m_LeaderRight)
            .inverted(true);

        m_LeaderLeft.configure(globalLeaderConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        m_FollowerLeft.configure(leftFollowerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        m_LeaderRight.configure(rightLeaderConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        m_FollowerRight.configure(rightFollowerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }
    public static void winchInit(SparkMax m_winchLeft,SparkMax m_winchRight) {
        SparkMaxConfig globalWinchLeaderConfig = new SparkMaxConfig();
        SparkMaxConfig rightFollowerWinchConfig = new SparkMaxConfig();
            globalWinchLeaderConfig
                .smartCurrentLimit(50)
                .idleMode(IdleMode.kBrake);

            rightFollowerWinchConfig
                .apply(globalWinchLeaderConfig)
                .follow(m_winchLeft);

        m_winchLeft.configure(globalWinchLeaderConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        m_winchRight.configure(rightFollowerWinchConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    }

