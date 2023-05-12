// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Constants;
import frc.robot.Constants.ArmConstants;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.LimeLight;
import frc.robot.subsystems.NavX;

public final class Autos {
  /** Example static factory for an autonomous command. */
  public static CommandBase lookAtTargetAuto(Drivetrain drive, LimeLight light) {
    return new LookAtTarget(drive, light);
  }

  public static CommandBase driveStraightAuto(Drivetrain drive, NavX gyro, double distance) {
    return new DriveStraight(drive, gyro, distance, Constants.straightDrivePower);
  }

  public static CommandBase autoBalanceAuto(Drivetrain drive, NavX gyro){
    return new AutoBalance(drive, gyro);
  }

  public static CommandBase turnDegreesAuto(Drivetrain drive, NavX gyro, double setPointHeading){
    return new TurnDegrees(drive, gyro, setPointHeading);
  }

  public static CommandBase BasicSequentialAuto(Drivetrain drive, NavX gyro, LimeLight light, Arm arm, Claw claw){
    return new BasicSequential(drive, gyro, light, arm, claw);
  }

  public static CommandBase ClimbChargeStationAuto(Drivetrain drive, NavX gyro){
    return new ClimbChargeStation(drive, gyro);
  }

  public static CommandBase ApproachGridAuto(Drivetrain drive, LimeLight light){
    return new ApproachGrid(drive, light);
  }

  // https://docs.wpilib.org/en/stable/docs/software/commandbased/command-compositions.html
  public static CommandBase R2(Drivetrain drive, NavX gyro, LimeLight light, Arm arm, Claw claw) {
    return Commands.sequence(
      Commands.deadline(
        Commands.sequence(new MoveArm(arm, ArmConstants.midGoalConePos), new ApproachGrid(drive, light)), 
        new CloseClaw(claw)),
      new OpenClaw(claw),
      Commands.parallel(new DriveStraight(drive, gyro, -60, .6), new MoveArm(arm, ArmConstants.StowedPos)),
      new DriveOntoCharge(drive, gyro, 1),
      new AutoBalance(drive, gyro)
    );
  }

  public static CommandBase limelightfreeAuto(Drivetrain drive, NavX gyro, Arm arm, Claw claw) {
    return Commands.sequence(
      new CloseClawTimer(claw),
      Commands.deadline(
        new MoveArm(arm, ArmConstants.midGoalConePos), 
        new CloseClaw(claw)),
      new OpenClaw(claw)
      /*Commands.parallel(new DriveStraight(drive, gyro, -60, .6), new MoveArm(arm, ArmConstants.StowedPos)),
      new DriveOntoCharge(drive, gyro, 1),
      new AutoBalance(drive, gyro)*/
    );
  }

  public static CommandBase R2Simple(Drivetrain drive, NavX gyro, LimeLight light, Arm arm, Claw claw) {
    return Commands.sequence(
      // Commands.deadline(
      //   Commands.sequence(new MoveArm(arm, ArmConstants.midGoalConePos), new ApproachGrid(drive, light)), 
      //   new CloseClaw(claw)),
      // new OpenClaw(claw),
      Commands.parallel(new DriveStraight(drive, gyro, -80, 1), new MoveArm(arm, ArmConstants.StowedPos)),
      new DriveOntoCharge(drive, gyro, 1),
      new AutoBalance(drive, gyro)
    );
  }

  public static CommandBase ClawClose(Claw claw) {
    return new CloseClaw(claw);
  }

  public static CommandBase moveArmAuto(Arm arm, double position){
    return new MoveArm(arm, position);
  }

  public static CommandBase bumperCars(Drivetrain drive, NavX gyro, Arm arm) {
    return Commands.deadline(
      Commands.sequence(
        new DriveStraight(drive, gyro, -6, .6),
        new DriveStraight(drive, gyro, 3, .6),
        new DriveStraight(drive, gyro, -4, .6),
        new DriveStraight(drive, gyro, 81, .7)),
      new MoveArm(arm, ArmConstants.StowedPos));
  }

  public static CommandBase gamerR1(Drivetrain drive, NavX gyro, LimeLight light, Arm arm, Claw claw) {
    return Commands.sequence(
      new CloseClawTimer(claw),
      Commands.deadline(
        Commands.sequence(
          new MoveArm(arm, ArmConstants.midGoalConePos),
          new ApproachGrid(drive, light)),
        new CloseClaw(claw)),
      new OpenClaw(claw),
      Commands.parallel(new MoveArm(arm, ArmConstants.StowedPos), new DriveStraight(drive, gyro, -75, .7))
    );
  }

  public static CommandBase dockWithCharge(Drivetrain drive, NavX gyro, Arm arm) {
    return Commands.deadline(
      Commands.sequence(
        new DriveStraight(drive, gyro, -6, .6),
        new DriveStraight(drive, gyro, 3, .6),
        new DriveStraight(drive, gyro, -4, .6),
        new DriveStraight(drive, gyro, 45, .9)),
      new MoveArm(arm, ArmConstants.StowedPos)
    );
  }

  private Autos() {
    throw new UnsupportedOperationException("This is1 a utility class!");
  }
}
