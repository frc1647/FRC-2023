// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.LimeLight;
import frc.robot.subsystems.NavX;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  //private final Drivetrain m_Drivetrain;
  //private final LimeLight m_LimeLight;
  private final Drivetrain m_Drivetrain = new Drivetrain(this);
  //private final LimeLight m_LimeLight = new LimeLight(this);
  //private final NavX m_NavX = new NavX();
  //private final Claw m_Claw = new LimeLight(this);
  //private final Claw m_Claw;

  private final SendableChooser<Command> m_chooser = new SendableChooser<>();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  private final CommandJoystick m_RightStick = new CommandJoystick(OperatorConstants.kRightStickPort);
  private final CommandJoystick m_LeftStick = new CommandJoystick(OperatorConstants.kLeftStickPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();

    // Autonomous command chooser
    //m_chooser.setDefaultOption("Auto Balance", Autos.autoBalanceAuto(m_Drivetrain, m_NavX));
    //m_chooser.addOption("Drive Straight", Autos.driveStraightAuto(m_Drivetrain, m_NavX));
    //m_chooser.addOption("Look At Reflective Tape", Autos.lookAtTargetAuto(m_Drivetrain, m_LimeLight));
    //m_chooser.addOption("Turn Degrees", Autos.turnDegreesAuto(m_Drivetrain, m_NavX, Constants.AutoTurnDegreesHeadingSetPoint));
    //SmartDashboard.putData(m_chooser);
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */ 
  private void configureBindings() {
    //m_driverController.b().onTrue(m_Claw.closeClaw());
    //m_driverController.a().onTrue(m_Claw.openClaw());
  }

  public Drivetrain getDriveTrain() {
    return this.m_Drivetrain;
  }

  /*public LimeLight getLimeLight() {
    return this.m_LimeLight;
  }*/

  //public Claw getClaw(){
  //  return this.m_Claw;
  //}

  public CommandXboxController getDriveController() {
    return this.m_driverController;
  }

  public CommandJoystick getRightStick() {
    return this.m_RightStick;
  }

  public CommandJoystick getLeftStick() {
    return this.m_LeftStick;
  }

  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    //return Autos.autoBalanceAuto(m_Drivetrain, m_NavX);
    return m_chooser.getSelected();
  }
}