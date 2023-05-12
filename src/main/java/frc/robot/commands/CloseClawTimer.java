package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants;
import frc.robot.subsystems.Claw;

public class CloseClawTimer extends CommandBase {

    Claw m_claw;
    Timer clock;
    double initTime;

    public CloseClawTimer(Claw claw) {
        m_claw = claw;
        clock = new Timer();
        initTime = clock.get();
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

    @Override
    public boolean isFinished() {
        return (clock.get() > 1 + clock.get());
    }
}