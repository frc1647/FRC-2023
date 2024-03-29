// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.Constants;

//Phoenix
//import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
//REV
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxRelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;


public class Drivetrain extends SubsystemBase {
  private RobotContainer robotContainer; //Define variable
  /** Creates a new Drivetrain. */
  DifferentialDrive m_robotDrive;
  private RelativeEncoder m_rightEncoder, m_leftEncoder;
  private double rightPosition, leftPosition;

  CANSparkMax m_frontLeft;
  CANSparkMax m_frontRight;
  CANSparkMax m_backRight;
  CANSparkMax m_backLeft;

  double speedMultiplier = 1;
  
  public Drivetrain(RobotContainer robotContainer) { // Taking in as parameter
    this.robotContainer = robotContainer;
    //Relate variable and parameter

    //WPI_VictorSPX is a constructor that initializes the motors to be used in code
    //now CANSparkMax
    this.m_frontLeft = new CANSparkMax(Constants.FRONTLEFTCAN, MotorType.kBrushless);
    this.m_backLeft = new CANSparkMax(Constants.BACKLEFTCAN, MotorType.kBrushless);
    this.m_frontRight = new CANSparkMax(Constants.FRONTRIGHTCAN, MotorType.kBrushless);
    this.m_backRight = new CANSparkMax(Constants.BACKRIGHTCAN, MotorType.kBrushless);

    m_frontLeft.setIdleMode(IdleMode.kCoast);
    m_frontRight.setIdleMode(IdleMode.kCoast);
    m_backLeft.setIdleMode(IdleMode.kCoast);
    m_backRight.setIdleMode(IdleMode.kCoast);

    MotorControllerGroup m_left = new MotorControllerGroup(m_frontLeft, m_backLeft);
    MotorControllerGroup m_right = new MotorControllerGroup(m_frontRight, m_backRight);

    m_right.setInverted(true);

    //DifferentialDrive differentitates the speed of the motors
    this.m_robotDrive = new DifferentialDrive(m_left, m_right);

    m_rightEncoder = m_frontRight.getEncoder(SparkMaxRelativeEncoder.Type.kHallSensor, 42);
    m_leftEncoder = m_frontLeft.getEncoder(SparkMaxRelativeEncoder.Type.kHallSensor, 42);
  }

  public void drive(double leftSpeed, double rightSpeed) {
    if (Math.abs(leftSpeed) > .05 || Math.abs(rightSpeed) > .05) {
      m_robotDrive.tankDrive(leftSpeed  * speedMultiplier, rightSpeed  * speedMultiplier);
    } else {
      m_robotDrive.tankDrive(0, 0);
    }
  }

  public void turnClockwiseForLight(double turn){
    if (robotContainer.getLimeLight().getArea() !=  0) {
      m_robotDrive.tankDrive(-turn, turn);
    }
  }

  public void approachGridDrive(double turn){
    if (robotContainer.getLimeLight().getArea() !=  0) {
      if (robotContainer.getLimeLight().getVerticalArea() < Constants.verticalAreaLimelight)
      m_robotDrive.tankDrive(-turn + Constants.ApproachSpeed, turn + Constants.ApproachSpeed);
    }
  }

  public void turnCounterClockwise(double turn){
    m_robotDrive.tankDrive(turn, -turn);
  }

  @Override
  public void periodic() {
    leftPosition = m_leftEncoder.getPosition();
    rightPosition = m_rightEncoder.getPosition();
  }

  public double getLeftPosition() {
    return leftPosition;
  }

  public double getRightPosition() {
    return rightPosition;
  }

  public void setCoastMode() {
    m_frontLeft.setIdleMode(IdleMode.kCoast);
    m_frontRight.setIdleMode(IdleMode.kCoast);
    m_backLeft.setIdleMode(IdleMode.kCoast);
    m_backRight.setIdleMode(IdleMode.kCoast);
  }

  public void setBrakeMode() {
    m_frontLeft.setIdleMode(IdleMode.kBrake);
    m_frontRight.setIdleMode(IdleMode.kBrake);
    m_backLeft.setIdleMode(IdleMode.kBrake);
    m_backRight.setIdleMode(IdleMode.kBrake);
  }

  public void setSlowMode() {
    speedMultiplier = .35;
    setBrakeMode();
  }

  public void setFastMode() {
    speedMultiplier = 1;
    setCoastMode();
  }

  public CommandBase setCoastModeCommand() {
    return runOnce(
      () -> {
        m_frontLeft.setIdleMode(IdleMode.kCoast);
        m_frontRight.setIdleMode(IdleMode.kCoast);
        m_backLeft.setIdleMode(IdleMode.kCoast);
      m_backRight.setIdleMode(IdleMode.kCoast);}
    );
  }

  public CommandBase setBrakeModeCommand() {
    return runOnce(
      () -> {
        m_frontLeft.setIdleMode(IdleMode.kBrake);
        m_frontRight.setIdleMode(IdleMode.kBrake);
        m_backLeft.setIdleMode(IdleMode.kBrake);
        m_backRight.setIdleMode(IdleMode.kBrake);}
    );
  }

public Command setSlowModeCommand() {
    return runOnce(
      () -> {
        setSlowMode();}
    );
  }

  public Command setFastModeCommand() {
    return runOnce(
      () -> {
        setFastMode();}
    );
  }
}
