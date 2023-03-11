package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;

import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.LimeLight;

/*public class LookAtTarget extends PIDCommand {
    public LookAtTarget(Drivetrain drive, LimeLight light) {
        super(
            new PIDController(Constants.LookAtTargetP, Constants.LookAtTargetI, Constants.LookAtTargetD),
            light::getXcor,// Double Supplier
            0.0,
            output -> drive.turnClockwiseForLight(output),// Double Consumer
            drive
        );
    } 
}*/