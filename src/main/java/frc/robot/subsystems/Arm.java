package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;


import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;


import frc.robot.Constants.ArmConstants;
import frc.robot.RobotContainer;

public class Arm extends PIDSubsystem{
    private RobotContainer robotContainer;

    WPI_TalonFX m_leftArm;
    double encoderPosition, encoderRadians;

    public Arm(RobotContainer robotContainer){

        super(new PIDController(ArmConstants.kPIDLoopP, ArmConstants.kPIDLoopI, ArmConstants.kPIDLoopD));
        getController().setTolerance(ArmConstants.Tolerance);
        setSetpoint(ArmConstants.StowedPos);
    

        this.robotContainer = robotContainer;
        //WPI_TalonFX m_rightArm = new WPI_TalonFX(Constants.RIGHTARMCAN);
        m_leftArm = new WPI_TalonFX(ArmConstants.LEFTARMCAN);
        m_leftArm.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
        //m_leftArm.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, Constants.kClawPIDLoopIdx, Constants.kClawTimeoutMs);
        encoderPosition = m_leftArm.getSelectedSensorPosition(); 

        m_leftArm.configFactoryDefault();
        m_leftArm.setNeutralMode(NeutralMode.Brake);

        /*m_leftArm.setSensorPhase(false);
        m_leftArm.configAllowableClosedloopError(Constants.kArmPIDLoopIdx, Constants.kArmAllowableError, 0);
        m_leftArm.config_kP(Constants.kArmPIDLoopIdx, Constants.kArmPIDLoopP);
        m_leftArm.config_kI(Constants.kArmPIDLoopIdx, Constants.kArmPIDLoopI);
        m_leftArm.config_kD(Constants.kArmPIDLoopIdx, Constants.kArmPIDLoopD);*/
    }

    @Override
    public void periodic(){
        encoderPosition = m_leftArm.getSelectedSensorPosition(); 
        encoderRadians = encoderPosition * ArmConstants.EncoderRadians;
        SmartDashboard.putNumber("Arm Setpoint", getController().getSetpoint());
        SmartDashboard.putNumber("Arm P", getController().getP()); 
        SmartDashboard.putNumber("Arm I", getController().getI());
        SmartDashboard.putNumber("Arm D", getController().getD());
        super.periodic(); //calls use output
    }

    public double getEncoderPosition() {
        return encoderPosition;
    }

    public double getEncoderPositionRadians() {
        return encoderRadians;
    }

    @Override
    public void useOutput(double output, double setpoint) {
        //m_leftArm.setVoltage(output + ArmConstants.MotorTorqueMultiplier* Math.cos(encoderRadians));//Arm torque multiplier is currently 0
        if (output < 0) {
            m_leftArm.setVoltage(output * .7);
        } else {
            m_leftArm.setVoltage(output);
        }
    }

    @Override
    public double getMeasurement() {
        return encoderPosition;
    }

    public boolean atSetpoint() {
        return getController().atSetpoint();
    }

    public CommandBase UpSimple(){
        return runOnce(
            () -> {
                m_leftArm.set(ArmConstants.UpSpeed);//Sets the speed of the arm going up
            }
        );
    }

    public CommandBase DownSimple(){
        return runOnce(
            () -> {
                m_leftArm.set(-ArmConstants.DownSpeed);
            }
        );
    }

    public void manualControl(double input){
        m_leftArm.set(0.4 * input); 
    }

    // public void enablePID() {
    //     getController().setP(ArmConstants.kPIDLoopP);
    //     getController().setI(ArmConstants.kPIDLoopI);
    //     getController().setD(ArmConstants.kPIDLoopD);
    // }
    
    // public void disablePID() {           //was called in robot teleopinit
    //     getController().setP(0);
    //     getController().setI(0);
    //     getController().setD(0);
    // }

    
    // public void testArmPID() {
    //     getController().setP(SmartDashboard.getNumber("Arm P", 0)); 
    //     getController().setI(SmartDashboard.getNumber("Arm I", 0));
    //     getController().setD(SmartDashboard.getNumber("Arm D", 0));
    //     setSetpoint(SmartDashboard.getNumber("Arm Setpoint", encoderPosition));
    // }

    // public void testArmPIDInit() {
    //     SmartDashboard.putNumber("Arm P", 0); 
    //     SmartDashboard.putNumber("Arm I", 0);
    //     SmartDashboard.putNumber("Arm D", 0);
    //     SmartDashboard.putNumber("Arm Setpoint", encoderPosition);
    // }
}