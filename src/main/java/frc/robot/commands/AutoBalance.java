package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.NavX;

public class AutoBalance extends PIDCommand{

    double Heading;
    NavX m_gyro;
    Drivetrain m_drive;
    double yawError;

    public AutoBalance(Drivetrain drive, NavX gyro){
        super(
            new PIDController(Constants.AutoBalanceP, Constants.AutoBalanceI, Constants.AutoBalanceD), 
            gyro::getPitch, 
            0, 
            Output -> drive.drive(-Output, -Output), //negative out put b/c when pitch is positive(front up)  (not used, Overrided)
            drive);

        this.m_gyro = gyro;
        this.m_drive = drive;
    }

    @Override
    public void initialize() {
        m_controller.reset();   //copied from definition
        this.Heading = m_gyro.getHeading();
    }

    @Override
    public void execute() {
        yawError = (m_gyro.getHeading() - Heading) * Constants.AutoBalanceHeadingErrorP;
        m_drive.drive(-m_controller.calculate(m_measurement.getAsDouble(), m_setpoint.getAsDouble()) - yawError + Constants.AutoBalanceBias, -m_controller.calculate(m_measurement.getAsDouble(), m_setpoint.getAsDouble()) + yawError + Constants.AutoBalanceBias);
    }
}
