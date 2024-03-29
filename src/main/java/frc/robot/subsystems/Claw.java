package frc.robot.subsystems;

//import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Claw extends SubsystemBase {
    //private RobotContainer robotContainer;

    WPI_TalonFX m_claw;

    public Claw() {
        //this.robotContainer = robotContainer;

        this.m_claw = new WPI_TalonFX(Constants.CLAWCAN);

        m_claw.configFactoryDefault();
        m_claw.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, Constants.kClawPIDLoopIdx, Constants.kClawTimeoutMs);
        m_claw.getSelectedSensorPosition();

        m_claw.setNeutralMode(NeutralMode.Brake);
        //m_claw.setSensorPhase(false);
        //m_claw.configAllowableClosedloopError(Constants.kClawPIDLoopIdx, Constants.kCLawAllowableError, 0);
        //m_claw.config_kP(Constants.kClawPIDLoopIdx, Constants.kClawPIDLoopP);
        //m_claw.config_kI(Constants.kClawPIDLoopIdx, Constants.kClawPIDLoopI);
        //m_claw.config_kD(Constants.kClawPIDLoopIdx, Constants.kClawPIDLoopD);

        // Armen Idea
        //m_claw.configForwardLimitSwitchSource(TalonFXFeedbackDevice.IntegratedSensor, null, 0)
    }

    public void periodic() {
        SmartDashboard.putNumber("Claw Temp", m_claw.getTemperature());
    }

    public double getEncoderPosition() {
        return this.m_claw.getSelectedSensorPosition();
    }

    public void reset(){
    }

    public void motorSetSpeed(double d) {
        m_claw.set(.35 * d);
    }

    public CommandBase closeClawCube(){ //being used
        return runOnce(
            () -> {
                //m_claw.set(ControlMode.Position, Constants.clawOpenPosition);
                m_claw.set(.1);
            }
        );
    }

    public CommandBase closeClawCone() {//being used
        return runOnce(
            () -> {
                m_claw.set(.25);
            }
        );
    }

    public CommandBase openClaw() {//being used
        return runOnce(
            () -> {
                m_claw.set(-.1);
            }
        );
    }

    public CommandBase stop() {
        return runOnce(
            () -> {
                m_claw.set(0);
            }
        );
    }
}
