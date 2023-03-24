package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;

public class MoveArm extends CommandBase {
    public MoveArm(Arm arm, double position) {
        arm.setSetpoint(position);
    }
}
