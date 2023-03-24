// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/** The VM is configured to automatically run this class, and to call the functions corresponding to
 *
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
    m_robotContainer.getLimeLight().setCamMode(1);
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
    m_robotContainer.getLimeLight().LimeLightPeriodic();
    SmartDashboard.putNumber("Arm ", m_robotContainer.getArm().getEncoderPosition());
    SmartDashboard.putNumber("Left Drive", m_robotContainer.getDriveTrain().getLeftPosition());
    SmartDashboard.putNumber("Right Drive", m_robotContainer.getDriveTrain().getRightPosition());
    SmartDashboard.putNumber("Claw ", this.m_robotContainer.getClaw().getEncoderPosition());
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
    m_robotContainer.getLimeLight().setCamMode(1);
  }

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    m_robotContainer.getArm().enablePID();
    m_robotContainer.getGyro().reset();
    m_robotContainer.getLimeLight().setCamMode(0);
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();
    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    //this.m_robotContainer.getAutonomousCommand();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    m_robotContainer.getLimeLight().setCamMode(1);
    m_robotContainer.getArm().disablePID();
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    this.m_robotContainer.getDriveTrain().drivePeriodic();
    this.m_robotContainer.getArm().manualControl(-m_robotContainer.getOperatorController().getLeftY());
    this.m_robotContainer.getClaw().motorSetSpeed(-m_robotContainer.getOperatorController().getRawAxis(4));
  }

  @Override
  public void testInit() {
    m_robotContainer.getLimeLight().setCamMode(0);
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
