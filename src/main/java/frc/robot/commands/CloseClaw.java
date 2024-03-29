package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Claw;

public class CloseClaw extends CommandBase {

    Claw m_claw;

    public CloseClaw(Claw claw) {
        m_claw = claw;
        addRequirements(m_claw);
    }

    @Override
    public void execute() {
        if (m_claw.getEncoderPosition() < 5300) {
            m_claw.motorSetSpeed(Constants.clawClosedPercentOutput);
        } else {
            m_claw.motorSetSpeed(0);
        }
    }
}