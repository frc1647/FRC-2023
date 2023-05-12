package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;

public class DefaultDrive extends CommandBase{
    private final Drivetrain m_drive;
    private RobotContainer robotContainer;

    public DefaultDrive(Drivetrain drive, RobotContainer robotContainer) {
        m_drive = drive;
        addRequirements(m_drive);
        this.robotContainer = robotContainer;
    }

    @Override
        public void execute() {
            this.m_drive.drive(-robotContainer.getDriveController().getLeftY(), -robotContainer.getDriveController().getRightY());
    }
}