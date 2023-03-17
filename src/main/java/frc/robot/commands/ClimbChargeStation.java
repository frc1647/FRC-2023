package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.commands.DriveStraight;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.NavX;
import frc.robot.subsystems.LimeLight;

public class ClimbChargeStation extends SequentialCommandGroup{
    public ClimbChargeStation(Drivetrain drive, NavX gyro) {
        addCommands(
            new DriveStraight(drive, gyro, 300, .5),
            new AutoBalance(drive, gyro)
        );
    }
}
