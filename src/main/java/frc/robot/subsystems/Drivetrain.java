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
  private RobotContainer robotContainer;
  /** Creates a new Drivetrain. */
  
  // Motor Controllers
  MotorControllerGroup m_left;
  MotorControllerGroup m_right;

  DifferentialDrive m_robotDrive;



  
  public Drivetrain(RobotContainer robotContainer) {
    this.robotContainer = robotContainer;

    var m_frontLeft = new WPI_VictorSPX(Constants.FRONTLEFTCAN);
    var m_backLeft = new WPI_VictorSPX(Constants.BACKLEFTCAN);
    var m_frontRight = new WPI_VictorSPX(Constants.FRONTRIGHTCAN);
    var m_backRight = new WPI_VictorSPX(Constants.BACKRIGHTCAN);

    this.m_left = new MotorControllerGroup(m_frontLeft, m_backLeft);
    this.m_right = new MotorControllerGroup(m_frontRight, m_backRight);

    this.m_robotDrive = new DifferentialDrive(m_left, m_right);
  }

  public void drive(double leftSpeed, double rightSpeed) {
    m_robotDrive.tankDrive(leftSpeed, rightSpeed);
  }

  public void drivePeriodic() {
    this.drive(robotContainer.getDriveController().getLeftY(), robotContainer.getDriveController().getRightY());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
