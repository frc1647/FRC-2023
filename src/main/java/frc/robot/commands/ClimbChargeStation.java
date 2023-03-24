package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.NavX;

public class ClimbChargeStation extends SequentialCommandGroup{
    public ClimbChargeStation(Drivetrain drive, NavX gyro) {
        addCommands(
            new DriveStraight(drive, gyro, -50, Constants.straightDrivePower),
            new DriveOntoCharge(drive, gyro, -1),
            new AutoBalance(drive, gyro)
        );
    }
}
