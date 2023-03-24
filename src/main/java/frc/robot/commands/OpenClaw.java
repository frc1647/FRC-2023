package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.Claw;

public class OpenClaw extends CommandBase {

    Claw m_claw;

    public OpenClaw(Claw claw) {
        m_claw = claw;
    }

    @Override
    public void execute() {
        if (m_claw.getEncoderPosition() < 3200) {
            m_claw.motorSetSpeed(-.15);
        } else {
            m_claw.motorSetSpeed(0);
        }
    }
}