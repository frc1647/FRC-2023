package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.DriveStraight;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.NavX;
import frc.robot.subsystems.LimeLight;

public class BasicSequential extends SequentialCommandGroup{
    public BasicSequential(Drivetrain drive, NavX gyro, LimeLight light, Arm arm, Claw claw) {
        addCommands(
            new DriveStraight(drive, gyro, 659, Constants.straightDrivePower),

            new TurnDegrees(drive, gyro, 180)

            //new DriveStraight(drive, gyro, 659),

            //new Arm up
            //new ApproachGrid(drive, light),
            //new claw open
        );
    }
}
