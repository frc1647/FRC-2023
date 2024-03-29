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
    public static final int kLeftStickPort = 2; // used for arm
    public static final int kOperatorControllerPort = 3;
    public static final int ClawAxis = 4;
  }

  public static class ArmConstants {
    //Arm
    public static final int LEFTARMCAN = 14;
    public static final int kPIDLoopIdx = 0;
    public static final double kAllowableError = 0;
    public static final double kPIDLoopP = .005;
    public static final double kPIDLoopI = 0.0009;
    public static final double kPIDLoopD = 0.00004;
    public static final double midGoalConePos = 30000;
    public static final double StowedPos = 160;
    public static final double loadingDockPos = 3535;
    public static final double UpSpeed = .2;
    public static final int DownSpeed = 0;
    public static final double Tolerance = 0;
    public static final double MotorTorqueMultiplier = 0; //(Motor Torque multiplier)(Mass of Arm)*(distance from pivot to COM of Arm)
    public static final double EncoderRadians = 5.555e-5; //2pi / 2048*GR, to rotation rn
    public static final double AutoTolerance = 1000;
  }

  //Drivetrain
  public static final int FRONTLEFTCAN = 1;
  public static final int BACKLEFTCAN = 2;
  public static final int FRONTRIGHTCAN = 3;
  public static final int BACKRIGHTCAN = 4;
  
  //Claw
  public static final int CLAWCAN = 13;
  public static final int kClawPIDLoopIdx = 0; //0 or 1
  public static final int kClawTimeoutMs = 0;
  public static final double kCLawAllowableError = 14; //random
  public static final double kClawPIDLoopP = 0.15; //just bc
  public static final double kClawPIDLoopI = 0.07;
  public static final double kClawPIDLoopD = 0.006;
  public static final double clawClosedPosition = -530; //4:1 gear ratio on motor 3:5 3D gear ratio 2048 encoder ticks per revolution
  public static final double clawOpenPosition = 3200;
  public static final double clawClosedPercentOutput = .3;

  //Look At Target
  public static final double LookAtTargetP = .015; //no more than .02
  public static final double LookAtTargetI = .01;
  public static final double LookAtTargetD = 0;

  //ApproachGrid
  public static final double verticalAreaLimelight = 22;
  public static final double ApproachSpeed = 0.2;

  //Drive Straight command
  public static final double straightDriveP = 0.01;
  public static final double straightDrivePower = .6;

  // Drive Onto Charge Station
  public static final double OntoChargeStationSpeed = .6;

  //Auto Balance on Charge Station
  public static final double autoBalanceMaxPower = 0.7;
  public static final double AutoBalanceP = .02;
  public static final double AutoBalanceI = 0.00001;
  public static final double AutoBalanceD = 0.005;
  public static final double AutoBalanceHeadingErrorP = 0.005;
  public static final double AutoBalanceBias = -0.02; //move forward slower than moving backward

  //Auto Turn Degrees
  public static final double TurnDegreesP = .04;
  public static final double TurnDegreesI = 0;
  public static final double TurnDegreesD = 0;
  public static final double turnDegreesMaxPower = 0.6;
}