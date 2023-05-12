package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ArmConstants;
import frc.robot.subsystems.Arm;

public class MoveArm extends CommandBase {

    Arm m_arm;
    double setpoint;

    public MoveArm(Arm arm, double position) {
        arm.setSetpoint(position);
        m_arm = arm;
        setpoint = position;
        System.out.println("Moving Arm");
    }

    @Override
    public boolean isFinished() {
        return Math.abs(m_arm.getEncoderPosition() - setpoint) < ArmConstants.AutoTolerance;
    }
}
