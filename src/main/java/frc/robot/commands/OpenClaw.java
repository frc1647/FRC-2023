package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Claw;

public class OpenClaw extends CommandBase {

    Claw m_claw;

    public OpenClaw(Claw claw) {
        m_claw = claw;
        addRequirements(m_claw);
    }

    @Override
    public void execute() {
        m_claw.motorSetSpeed(-.15);
    }

    @Override
    public boolean isFinished() {
        return m_claw.getEncoderPosition() < Constants.clawOpenPosition;
    }
}