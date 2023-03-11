package frc.robot.commands;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.NavX;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;

import edu.wpi.first.math.MathUtil;

public class TurnDegrees extends PIDCommand{
    public TurnDegrees(Drivetrain drive, NavX gyro, double SetPointHeading){
        super(
            new PIDController(Constants.TurnDegreesP, Constants.TurnDegreesI, Constants.TurnDegreesD),
            gyro::getHeading,
            SetPointHeading,
            output -> drive.turnCounterClockwise(MathUtil.clamp(output, -Constants.turnDegreesMaxPower, Constants.turnDegreesMaxPower)),
            drive
        );
    }
}
