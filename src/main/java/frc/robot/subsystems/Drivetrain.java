// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import frc.robot.RobotContainer;
import frc.robot.Constants;


public class Drivetrain extends SubsystemBase {
  private RobotContainer robotContainer; //Define variable
  /** Creates a new Drivetrain. */
  
  // Motor Controllers
  MotorControllerGroup m_left;  // left side motor group 
  MotorControllerGroup m_right;  // right left side motor group 

  DifferentialDrive m_robotDrive;

  
  public Drivetrain(RobotContainer robotContainer) { // Taking in as parameter
    this.robotContainer = robotContainer;
    //Relate variable and parameter
    //WPI_VictorSPX is a constructor that initializes the motors to be used in code
    var m_frontLeft = new WPI_VictorSPX(Constants.FRONTLEFTCAN);
    var m_backLeft = new WPI_VictorSPX(Constants.BACKLEFTCAN);
    var m_frontRight = new WPI_VictorSPX(Constants.FRONTRIGHTCAN);
    var m_backRight = new WPI_VictorSPX(Constants.BACKRIGHTCAN);

    m_frontRight.setInverted(true);
    m_backRight.setInverted(true);

    this.m_left = new MotorControllerGroup(m_frontLeft, m_backLeft);
    this.m_right = new MotorControllerGroup(m_frontRight, m_backRight);

    //DifferentialDrive differentitates the speed of the motors
    this.m_robotDrive = new DifferentialDrive(m_left, m_right);
  }

  public void drive(double leftSpeed, double rightSpeed) {
    //tankDrive is a method that controlls motors seperately
    m_robotDrive.tankDrive(leftSpeed, rightSpeed);
  }

  public void drivePeriodic() {
    //drive refers back tp the drive class
    this.drive(-1 * robotContainer.getDriveController().getLeftY(), -1 * robotContainer.getDriveController().getRightY());
    //this.drive(-1 * robotContainer.getLeftStick().getY(), -1 * robotContainer.getRightStick().getY());
  }

  /*public void turnClockwiseForLight(double turn){
    if (robotContainer.getLimeLight().getArea() !=  0) {
      m_robotDrive.tankDrive(-turn, turn);
    }
  }*/

  public void turnCounterClockwise(double turn){
    m_robotDrive.tankDrive(turn, -turn);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
