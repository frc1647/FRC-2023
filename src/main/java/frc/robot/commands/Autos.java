// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.LimeLight;
import frc.robot.subsystems.NavX;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Commands;

public final class Autos {
  /** Example static factory for an autonomous command. */
  public static CommandBase exampleAuto(ExampleSubsystem subsystem) {
    return Commands.sequence(subsystem.exampleMethodCommand(), new ExampleCommand(subsystem));
  }

  /*public static CommandBase lookAtTargetAuto(Drivetrain drive, LimeLight light) {
    return new LookAtTarget(drive, light);
  }*/

  public static CommandBase driveStraightAuto(Drivetrain drive, NavX gyro) {
    return new DriveStraight(drive, gyro);
  }

  public static CommandBase autoBalanceAuto(Drivetrain drive, NavX gyro){
    return new AutoBalance(drive, gyro);
  }

  public static CommandBase turnDegreesAuto(Drivetrain drive, NavX gyro, double setPointHeading){
    return new TurnDegrees(drive, gyro, setPointHeading);
  }

  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
