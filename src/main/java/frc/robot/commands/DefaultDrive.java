package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.DoubleSupplier;

import frc.robot.subsystems.Drivetrain;

public class DefaultDrive extends CommandBase{
    private final Drivetrain m_drive;
    private final DoubleSupplier m_left;
    private final DoubleSupplier m_right;

    public DefaultDrive(Drivetrain drive, DoubleSupplier left, DoubleSupplier right) {
        m_drive = drive;
        m_left = left;
        m_right = right;
        addRequirements(m_drive);
    }

    @Override
        public void execute() {
            //this.m_drive.drive(m_left, m_right);
    }



}