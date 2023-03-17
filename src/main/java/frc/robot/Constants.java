// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.subsystems.Drivetrain;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
    public static final int kRightStickPort = 1;
    public static final int kLeftStickPort = 2;
  }

  //Drivetrain
  public static final int FRONTLEFTCAN = 1;
  public static final int BACKLEFTCAN = 2;
  public static final int FRONTRIGHTCAN = 3;
  public static final int BACKRIGHTCAN = 4;
  
  //Claw
  public static final int CLAWCAN = 13;
  public static final int kClawPIDLoopIdx = 0;//0 or 1
  public static final int kClawTimeoutMs = 0;
  public static final double kCLawAllowableError = 14;//random
  public static final double kClawPIDLoopP = 0.15;//just bc
  public static final double kClawPIDLoopI = 0.07;
  public static final double kClawPIDLoopD = 0.006;
  public static final double clawClosedPosition = -530; // 4:1 gear ratio on motor 3:5 3D gear ratio 2048 encoder ticks per revolution
  public static final double clawOpenPosition = 2000;  // 90 degrees of motion 
  public static final double clawClosedPercentOutput = .15;

  //Look At Target
  public static final double LookAtTargetP = .02;//no more than .02
  public static final double LookAtTargetI = .015;
  public static final double LookAtTargetD = 0;

  //ApproachGrid
  public static final double verticalAreaLimelight = 10;
  public static final double ApproachSpeed = 0.1;

  //Drive Straight command
  public static final double straightDriveP = 0.04;
  public static final double straightDrivePower = .4;

  //Auto Balance on Charge Station
  public static final double autoBalanceMaxPower = 0.7;
  public static final double AutoBalanceP = .0485;
  public static final double AutoBalanceI = 0.00005;
  public static final double AutoBalanceD = 0.00;
  public static final double AutoBalanceHeadingErrorP = 0.035;
  public static final double AutoBalanceBias = -0.02;// move forward slower than moving backward

  //Auto Turn Degrees
  public static final double TurnDegreesP = .04;
  public static final double TurnDegreesI = 0;
  public static final double TurnDegreesD = 0;
  public static final double AutoTurnDegreesHeadingSetPoint = 90;
  public static final double turnDegreesMaxPower = 0.6;

  //Arm
  public static final int LEFTARMCAN = 14;
  public static final int kArmPIDLoopIdx = 0;
  public static final double kArmAllowableError = 0;
  public static final double kArmPIDLoopP = 0;
  public static final double kArmPIDLoopI = 0;
  public static final double kArmPIDLoopD = 0;
  public static final double midGoalConePos = 3355;
  public static final double ArmStowedPos = 160;
  public static final double loadingDockPos = 3535;
  public static final double UpSpeed = .2;
  public static final int DownSpeed = 0;
  public static final double ArmTolerance = 0;
  public static final double ArmMotorTorqueMultiplier = 0; //(Motor Torque multiplier)(Mass of Arm)*(distance from pivot to COM of Arm)
  public static final double ArmEncoderRadians = 0.003068/9; //2pi / 2048*GR
}