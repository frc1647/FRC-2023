package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import frc.robot.RobotContainer;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;


public class Claw extends SubsystemBase {
    private RobotContainer robotContainer;

    WPI_TalonFX m_claw;

    public Claw() {
        this.robotContainer = robotContainer;

        this.m_claw = new WPI_TalonFX(Constants.CLAWCAN);
        m_claw.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
        m_claw.getSelectedSensorPosition();
        
        m_claw.setNeutralMode(NeutralMode.Coast);
        m_claw.setSensorPhase(false);
        m_claw.configAllowableClosedloopError(Constants.kPIDLoopIdx, Constants.kCLawAllowableError, 0);
        m_claw.config_kP(Constants.kPIDLoopIdx, Constants.kPIDLoopP);
        m_claw.config_kI(Constants.kPIDLoopIdx, Constants.kPIDLoopI);
        m_claw.config_kD(Constants.kPIDLoopIdx, Constants.kPIDLoopD);

        // Armen Idea
        //m_claw.configForwardLimitSwitchSource(TalonFXFeedbackDevice.IntegratedSensor, null, 0)
    }

    public double getEncoderPosition() {
        return this.m_claw.getSelectedSensorPosition();
    }

    public CommandBase openClaw(){
        return runOnce(
            () -> {
                m_claw.set(ControlMode.Position, Constants.clawOpenPosition);
            }
        );
    }
    
    public CommandBase closeClaw(){
        return runOnce(
            () -> {
                m_claw.set(ControlMode.Position, Constants.clawClosedPosition);
            }
        );
    }

    /* 
    public CommandBase closeClawArmenIdea(){
        return runOnce(
            () -> {
                if (m_claw.isFwdLimitSwitchClosed()) {
                    m_claw.set(ControlMode.PercentOutput, .2);
                }
            }
        );
    }*/

    public void reset(){
    }

    public void motorSetSpeed(double d) {
        m_claw.set(d);
    }

}
