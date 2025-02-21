// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

// package frc.robot.Subroutines;

// import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj.Timer;

// public class Autonomous {
//     double MovementPosition_Offset = 0; //Positive = Lower Arm, Negative = Raise Arm. Range (-4 to +16)
//     double MovementPosition = 34 + MovementPosition_Offset; 

//     public void Init
// public void Initial(SendableChooser<String> Auto_Selector, String[] AutoNames){
//         SmartDashboard.putData("Auto choices", Auto_Selector);
//         Auto_Selector.setDefaultOption(AutoNames[0], AutoNames[0]);
//         Auto_Selector.addOption(AutoNames[1], AutoNames[1]);
//         Auto_Selector.addOption(AutoNames[2], AutoNames[2]);
//         Auto_Selector.addOption(AutoNames[3], AutoNames[3]);
//         Auto_Selector.addOption(AutoNames[4], AutoNames[4]);
//         Auto_Selector.addOption(AutoNames[5], AutoNames[5]);
//         Auto_Selector.addOption(AutoNames[6], AutoNames[6]);
//         Auto_Selector.addOption(AutoNames[7], AutoNames[7]);
        
//     }
// //~~~~~~~~~~~~~~~~~~~~~~~~~VVVV EDIT THESE HERE VVVV~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

//     public void Auto_StationaryScore(Driving DriveSub, Arm ArmSub, Pickup PickupSub, Shooter ShooterSub, double Tune, Timer TimerSub){

//         SmartDashboard.putString("Auto Command", "Preset Aim Shoot");
//         Preset_Aim(ArmSub, (ArmSub.getShootPosition() + Tune), true, TimerSub);
        
//         if(TimerSub.get() < 15){
//             SmartDashboard.putString("Auto Command", "Preset_Shooter");
//             Preset_Shoot(ShooterSub, PickupSub);
//         }
        
//         SmartDashboard.putString("Auto Command", "Arm to Movement Position");
//         Preset_Aim(ArmSub, ArmSub.getZero()+ MovementPosition, true, TimerSub);
//         SmartDashboard.putString("Auto Command", "Auto Finished");
//     }

//     public void Auto_Source(Driving DriveSub, Arm ArmSub, Pickup PickupSub, Shooter ShooterSub, String Alliance, Timer TimerSub){
//         double DistanceFT;
//         double wheelSpeed;
//         double TurnTune;
//         //Run Stationary Score
//         double armTune = 0.0; //Adjust +/- 5.0 to tune arm angle [Positive = lower arm, Negative = raise arm])
//         Auto_StationaryScore(DriveSub, ArmSub, PickupSub, ShooterSub, armTune, TimerSub);
        
//         //Preset_Move forward 
//         DistanceFT = 11.0; //Adjust to tune distance moved. [Positive value]
//         wheelSpeed = 0.25; //Note: Higher Speed will move further at the same DistanceFT due to coasting [Always positive value]
//         SmartDashboard.putString("Auto Command", "Preset Move Forward");
//         Preset_Move(DriveSub, DistanceFT, wheelSpeed, 0, TimerSub);
        
//         //Preset_Turn 60 degrees
//         if(Alliance == "RED"){
//             //Turn right 60 degrees
//             TurnTune = 0;
//             SmartDashboard.putString("Auto Command", "Preset_Turn Right 60");
//             Preset_Turn(DriveSub, "RIGHT", 60, TurnTune, TimerSub);
//             //If overturning, set tune between -1 & -20
//             //If underturning, set tune between 1 & 20
//         }
//         if(Alliance == "BLUE"){
//             //Turn Left 60 degrees
//             TurnTune = 0;
//             SmartDashboard.putString("Auto Command", "Preset_Turn Left 60");
//             Preset_Turn(DriveSub, "LEFT", 60, TurnTune, TimerSub);
//             //If overturning, set TurnTune between -1 & -20
//             //If underturning, set TurnTune between 1 & 20
//         }

//         //Lower Arm to LS_Front
//         ArmSub.SetSpeed(-0.6);

//         //Preset_Pickup
//         DistanceFT = 17.8; //Adjust to tune distance moved. [Positive value]
//         wheelSpeed = 0.25; //Note: Higher Speed will move further at the same DistanceFT due to coasting [Always positive value]
//         SmartDashboard.putString("Auto Command", "Preset_Pickup");
//         Preset_Pickup(PickupSub, DriveSub, DistanceFT, wheelSpeed, TimerSub);

        
//         //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//         //NOTE: Only activate if pickup successful in practice
//         /*
//         //Preset_Aim Lift Arm while running Pickup in
//         PickupSub.SetSpeed(-1);
//         SmartDashboard.putString("Auto Command", "Arm to Movement Position");
//         Preset_Aim(ArmSub, ArmSub.getZero()+ MovementPosition, true, TimerSub);
//         PickupSub.SetSpeed(0);

        
//         //Preset_Move Reverse
//         DistanceFT = -15.0; //Adjust to tune distance moved. [Negative value]
//         wheelSpeed = 0.25; //Note: Higher Speed will move further at the same DistanceFT due to coasting [Always positive value]
//         SmartDashboard.putString("Auto Command", "Preset Move Reverse");
//         Preset_Move(DriveSub, DistanceFT, wheelSpeed, 0, TimerSub);
//         */
       
//         SmartDashboard.putString("Auto Command", "Auto Finished");
//     }

//     public void Auto_Center(Driving DriveSub, Arm ArmSub, Pickup PickupSub, Shooter ShooterSub, String Alliance, Timer TimerSub){
//         double DistanceFT;
//         double wheelSpeed;
//         double TurnTune;
//         //Run Stationary Score
//         double armTune = 0.0; //Adjust +/- 5.0 to tune arm angle [positive = lower arm, negative = raise arm])
//         Auto_StationaryScore(DriveSub, ArmSub, PickupSub, ShooterSub, armTune, TimerSub);
        
//         //Drop arm to LS_Front
//         ArmSub.SetSpeedAuto(-1); 

//         //Preset_Pickup (Move out and attempt pickup)
//         DistanceFT = 3.5; //Adjust to tune distance moved. [Always positive value]
//         wheelSpeed = 0.4; //Note: Higher Speed will move further at the same DistanceFT due to coasting [Always positive value]
//         SmartDashboard.putString("Auto Command", "Preset_Pickup Attempt");
//         Preset_Pickup(PickupSub, DriveSub, DistanceFT, wheelSpeed, TimerSub);

//         //Move Arm to shooter position with running Pickup in
//         PickupSub.SetSpeed(-1);
//         SmartDashboard.putString("Auto Command", "Arm to Shoot Position");
//         Preset_Aim(ArmSub, ArmSub.getShootPosition(), true, TimerSub);
//         PickupSub.SetSpeed(-0.5);

//         //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
//         // Preset Move back to speaker
//         DistanceFT = -6.5;
//         wheelSpeed = 0.3;
//         SmartDashboard.putString("Auto Command", "Preset Move reverse");
//         Preset_Move(DriveSub, DistanceFT, wheelSpeed, 0, TimerSub);

//         // Shoot Ring 
//         SmartDashboard.putString("Auto Command", "Auto stationary");
//          armTune = -2.0; //Adjust +/- 5.0 to tune arm angle [positive = lower arm, negative = raise arm])
//         Auto_StationaryScore(DriveSub, ArmSub, PickupSub, ShooterSub, armTune, TimerSub);
        
//         // Preset Move away from speaker
//        /*  DistanceFT = 7.5;
//         wheelSpeed = 0.5;
//         SmartDashboard.putString("Auto Command", "Preset Move Forward");
//         Preset_Move(DriveSub, DistanceFT, wheelSpeed, 0, TimerSub);

//          // Preset Stop movement
//         DistanceFT = -0.03;
//         wheelSpeed = 0.5;
//         SmartDashboard.putString("Auto Command", "Preset Move reverse");
//         Preset_Move(DriveSub, DistanceFT, wheelSpeed, 0, TimerSub); */


//         SmartDashboard.putString("Auto Command", "Auto Finished");
//     }

//     public void Auto_Amp(Driving DriveSub, Arm ArmSub, Pickup PickupSub, Shooter ShooterSub, String Alliance, Timer TimerSub){
//         double DistanceFT;
//         double wheelSpeed;
//         double TurnTune;
//         //Run Stationary Score
//         double armTune = 0.0; //Adjust +/- 5.0 to tune arm angle [positive = lower arm, negative = raise arm])
//         Auto_StationaryScore(DriveSub, ArmSub, PickupSub, ShooterSub, armTune, TimerSub);
        
//         //Preset_Move forward 
//         DistanceFT = 1.0; //Adjust to tune distance moved. [Positive value]
//         wheelSpeed = 0.25; //Note: Higher Speed will move further at the same DistanceFT due to coasting [Always positive value]
//         SmartDashboard.putString("Auto Command", "Preset Move Forward");
//         Preset_Move(DriveSub, DistanceFT, wheelSpeed, 0, TimerSub);
        
//         //Preset_Turn 60 degrees
//         if(Alliance == "RED"){
//             //Turn Left 60 degrees
//             TurnTune = 0;
//             SmartDashboard.putString("Auto Command", "Preset_Turn Left 60");
//             Preset_Turn(DriveSub, "LEFT", 60, TurnTune, TimerSub);
//             //If overturning, set tune between -1 & -20
//             //If underturning, set tune between 1 & 20
//         }
//         if(Alliance == "BLUE"){
//             //Turn right 60 degrees
//             TurnTune = 0;
//             SmartDashboard.putString("Auto Command", "Preset_Turn Right 60");
//             Preset_Turn(DriveSub, "RIGHT", 60, TurnTune, TimerSub);
//             //If overturning, set TurnTune between -1 & -20
//             //If underturning, set TurnTune between 1 & 20
//         }

//         //Lower Arm to LS_Front
//         ArmSub.SetSpeed(-0.6);

//         //Preset_Pickup
//         DistanceFT = 23.6; //Adjust to tune distance moved. [Positive value]
//         wheelSpeed = 0.25; //Note: Higher Speed will move further at the same DistanceFT due to coasting [Always positive value]
//         SmartDashboard.putString("Auto Command", "Preset_Pickup");
//         Preset_Pickup(PickupSub, DriveSub, DistanceFT, wheelSpeed, TimerSub);

//         //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//         //NOTE: Only activate if pickup successful in practice
//         /*/        
//         //Preset_Aim Lift Arm while running Pickup in
//         PickupSub.SetSpeed(-1);
//         SmartDashboard.putString("Auto Command", "Arm to Movement Position");
//         Preset_Aim(ArmSub, ArmSub.getZero()+ MovementPosition, true, TimerSub);
//         PickupSub.SetSpeed(0);

        
//         //Preset_Move Reverse
//         DistanceFT = -20.0; //Adjust to tune distance moved. [Negative value]
//         wheelSpeed = 0.25; //Note: Higher Speed will move further at the same DistanceFT due to coasting [Always positive value]
//         SmartDashboard.putString("Auto Command", "Preset Move Reverse");
//         Preset_Move(DriveSub, DistanceFT, wheelSpeed, 0, TimerSub);
//         */
       
//         SmartDashboard.putString("Auto Command", "Auto Finished");
//     }

//     public void Auto_Center_Fast(Driving DriveSub, Arm ArmSub, Pickup PickupSub, Shooter ShooterSub, String Alliance, Timer TimerSub){
//         double DistanceFT;
//         double wheelSpeed;
//         double TurnTune;
//         //Run Stationary Score
//         double armTune = 0.0; //Adjust +/- 5.0 to tune arm angle [positive = lower arm, negative = raise arm])
//         Auto_StationaryScore(DriveSub, ArmSub, PickupSub, ShooterSub, armTune, TimerSub);
        
//         //Drop arm to LS_Front
//         ArmSub.SetSpeedAuto(-1); 

//         //Preset_Pickup (Move out and attempt pickup)
//         DistanceFT = 3.5; //Adjust to tune distance moved. [Always positive value]
//         wheelSpeed = 0.4; //Note: Higher Speed will move further at the same DistanceFT due to coasting [Always positive value]
//         SmartDashboard.putString("Auto Command", "Preset_Pickup Attempt");
//         Preset_Pickup(PickupSub, DriveSub, DistanceFT, wheelSpeed, TimerSub);

//         //Intake the pickup while raising arm
//         PickupSub.SetSpeed(-1);
//         ArmSub.SetSpeedAuto(0.4); 
//         //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
//         // Preset Move back to speaker
//         DistanceFT = -6.5;
//         wheelSpeed = 0.3;
//         SmartDashboard.putString("Auto Command", "Preset Move reverse");
//         Preset_Move(DriveSub, DistanceFT, wheelSpeed, 0, TimerSub);
        
//         //Stop Pickup intake
//         PickupSub.SetSpeed(0.5);

//         // Shoot Ring 
//         SmartDashboard.putString("Auto Command", "Auto stationary");
//          armTune = -2.0; //Adjust +/- 5.0 to tune arm angle [positive = lower arm, negative = raise arm])
//         Auto_StationaryScore(DriveSub, ArmSub, PickupSub, ShooterSub, armTune, TimerSub);
        
//         // Preset Move away from speaker
//        /*  DistanceFT = 7.5;
//         wheelSpeed = 0.5;
//         SmartDashboard.putString("Auto Command", "Preset Move Forward");
//         Preset_Move(DriveSub, DistanceFT, wheelSpeed, 0, TimerSub);

//          // Preset Stop movement
//         DistanceFT = -0.03;
//         wheelSpeed = 0.5;
//         SmartDashboard.putString("Auto Command", "Preset Move reverse");
//         Preset_Move(DriveSub, DistanceFT, wheelSpeed, 0, TimerSub); */


//         SmartDashboard.putString("Auto Command", "Auto Finished");
//     }


// //~~~~~~~~~~~~~~~~~~~~~^^^^ EDIT THESE HERE ^^^^^~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

//     public void Preset_Turn(Driving DriveSub, String direction, Integer angle, double tune, Timer TimerSub){
//         int i = 1;
//         double TurnDistance;
//         if (direction == "LEFT"){ i = -1;}
//         switch (angle) {
//             case 90:
//             TurnDistance = 1.6;
//             Preset_Move(DriveSub, ((TurnDistance + (tune/100)) * i), 0, (0.5 * i), TimerSub);
//             break;

//             case 60:
//             TurnDistance = 1.4;
//             Preset_Move(DriveSub, ((TurnDistance + (tune/100)) * i), 0, (0.5 * i), TimerSub);
//             break;

//             default:
//             System.out.println("Pick A Preset Angle");
//             break;

//         }
//     }

//     public void Preset_Shoot(Shooter ShooterSub, Pickup PickupSub){
//         double enc_shooterzero = ShooterSub.getEncoder();
//         double enc_pickupzero = PickupSub.getEncoder();
//         double enc_pickupTarget = 13;
//         double enc_shooterTarget = 16;
//         while(PickupSub.getEncoder() < (enc_pickupzero + 3.25)){
//         PickupSub.SetSpeed(1);
//         }
//         PickupSub.SetSpeed(0);

//         while(ShooterSub.getEncoder() > (enc_shooterzero - enc_shooterTarget)){
//             ShooterSub.SetSpeed(1, true);
//             if (ShooterSub.getEncoder() > (enc_shooterzero - enc_pickupTarget)){
//                 PickupSub.SetSpeed(-2); //THIS WAS CHANGED FROM -1 TO -2
//                 //MAKE SURE THAT THIS CHANGES BACK TO -1 IF YOU CHANGE SPEED OF INTAKE TO >0.5!!!!!
//             }
//         }
//         ShooterSub.SetSpeed(0, true);
//         PickupSub.SetSpeed(0);
//     }

//     public void Preset_Aim(Arm ArmSub, double enc_armEnd, Boolean ControllerInput, Timer TimerSub){
//         double enc_armStart = ArmSub.getEncoder();
//         double armSpeed = 0.6;
//         if (ControllerInput == true){
//             if (enc_armStart > enc_armEnd){
//                 while(ArmSub.getEncoder() > enc_armEnd && TimerSub.get() < 15){
//                     ArmSub.SetSpeed(armSpeed);
//                     SmartDashboard.putNumber("Arm Encoder",ArmSub.getEncoder());
//                 }
//             }
//             if (enc_armStart < enc_armEnd){
//                 while(ArmSub.getEncoder() < enc_armEnd && TimerSub.get() < 15){
//                     ArmSub.SetSpeed(-armSpeed);
//                     SmartDashboard.putNumber("Arm Encoder",ArmSub.getEncoder());
//                 }
//             }
//         }
//         ArmSub.SetSpeed(0.05);
//     }

//     public void Preset_Move(Driving DriveSub, double Distance_Feet, double speed, double rotation, Timer TimerSub){
//         double targetSteps = DriveSub.Feet2Steps(Distance_Feet);
//         if (Distance_Feet > 0){
//         while(DriveSub.getEncoder() < targetSteps && TimerSub.get() < 15){
//                 DriveSub.AutoDrive(-speed, +rotation);
//                 //SmartDashboard.putNumber("Target Steps", targetSteps);
//                 //SmartDashboard.putNumber("Wheel Encoder", DriveSub.getEncoder());
//         }
//         }
//         if (Distance_Feet < 0){
//             while(DriveSub.getEncoder() > targetSteps && TimerSub.get() < 15){
//                 DriveSub.AutoDrive(speed, +rotation);
//                 //SmartDashboard.putNumber("Target Steps", targetSteps);
//                 //SmartDashboard.putNumber("Wheel Encoder", DriveSub.getEncoder());
//         }
//         }
//     }

//     public void Preset_Pickup(Pickup PickupSub, Driving DriveSub, double Distance_Feet, double speed, Timer TimerSub){
//         double targetSteps = DriveSub.Feet2Steps(Distance_Feet);
//         if (Distance_Feet > 0){
//         while(DriveSub.getEncoder() < targetSteps && TimerSub.get() < 15){
//                 DriveSub.AutoDrive(-speed, 0);
//                 //SmartDashboard.putNumber("Wheel Encoder",DriveSub.getEncoder());
//                 if (DriveSub.getEncoder() > (targetSteps-10)){
//                     PickupSub.SetSpeed(-1);
//                 }
//         }
//         }       
//                 DriveSub.AutoDrive(0, 0);
//                 PickupSub.SetSpeed(0);           
//     }
// }
