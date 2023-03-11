// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
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

  public static final int FRONTLEFTCAN = 5;
  public static final int BACKLEFTCAN = 10;
  public static final int FRONTRIGHTCAN = 9;
  public static final int BACKRIGHTCAN = 2;
  
  public static final int CLAWCAN = 16;
  public static final int kPIDLoopIdx = 0;//0 or 1
  public static final int kTimeoutMs = 0;
  public static final double kCLawAllowableError = 7;//random
  public static final double kPIDLoopP = 0.06;//just bc
  public static final double kPIDLoopI = 0.0013;
  public static final double kPIDLoopD = 0.01;
  public static final double clawClosedPosition = 0; // 4:1 gear ratio on motor 3:5 3D gear ratio 2048 encoder ticks per revolution
  public static final double clawOpenPosition = 3413;  // 90 degrees of motion 

  //Limelight
  public static final double LookAtTargetP = .02;//no more than .02
  public static final double LookAtTargetI = .015;
  public static final double LookAtTargetD = 0;

  //Drive Straight command
  public static final double straightDriveP = 0.04;
  public static final double straightDrivePower = .4;
  public static final double straightDriveTime = 2.0; //in seconds

  //Auto Balance on Charge Station
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

}