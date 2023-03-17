package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;


import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Arm extends PIDSubsystem{
    private RobotContainer robotContainer;

    WPI_TalonFX m_leftArm;
    double encoderPosition;
    double encoderRadians;

    public Arm(RobotContainer robotContainer){

        super(new PIDController(Constants.kArmPIDLoopP, Constants.kArmPIDLoopI, Constants.kArmPIDLoopD));
        getController().setTolerance(Constants.ArmTolerance);
        setSetpoint(Constants.ArmStowedPos);
    

        this.robotContainer = robotContainer;
        //WPI_TalonFX m_rightArm = new WPI_TalonFX(Constants.RIGHTARMCAN);
        m_leftArm = new WPI_TalonFX(Constants.LEFTARMCAN);
        m_leftArm.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
        //m_leftArm.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, Constants.kClawPIDLoopIdx, Constants.kClawTimeoutMs);
        encoderPosition = m_leftArm.getSelectedSensorPosition(); 

        m_leftArm.configFactoryDefault();
        m_leftArm.setNeutralMode(NeutralMode.Coast);

        /*m_leftArm.setSensorPhase(false);
        m_leftArm.configAllowableClosedloopError(Constants.kArmPIDLoopIdx, Constants.kArmAllowableError, 0);
        m_leftArm.config_kP(Constants.kArmPIDLoopIdx, Constants.kArmPIDLoopP);
        m_leftArm.config_kI(Constants.kArmPIDLoopIdx, Constants.kArmPIDLoopI);
        m_leftArm.config_kD(Constants.kArmPIDLoopIdx, Constants.kArmPIDLoopD);*/
    }

    @Override
    public void periodic(){
        encoderPosition = m_leftArm.getSelectedSensorPosition(); 
        encoderRadians = encoderPosition*Constants.ArmEncoderRadians;
        super.periodic();
    }

    public double getEncoderPosition() {
        return encoderPosition;
    }

    @Override
    public void useOutput(double output, double setpoint) {
        m_leftArm.setVoltage(output + Constants.ArmMotorTorqueMultiplier* Math.cos(encoderRadians));
    }

    @Override
    public double getMeasurement() {
        return getEncoderPosition();
    }

    public boolean atSetpoint() {
        return m_controller.atSetpoint();//Returns true if the error is within the tolernce fo the setpoint
    }





    public CommandBase UpSimple(){
        return runOnce(
            () -> {
                m_leftArm.set(Constants.UpSpeed);//Sets the speed of the arm going up
            }
        );
    }

    public CommandBase DownSimple(){
        return runOnce(
            () -> {
                m_leftArm.set(-Constants.DownSpeed);//Sets the speed of the arm going up
            }
        );
    }

    public void manualControl(){
        m_leftArm.set(-0.4 * robotContainer.getLeftStick().getY()); //Sets the y positioning of the joystick
    }
}
