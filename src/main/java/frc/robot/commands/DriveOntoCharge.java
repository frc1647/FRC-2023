package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;

//import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.NavX;


// goes backwards
public class DriveOntoCharge extends CommandBase {

    NavX m_gyro;
    Drivetrain m_drive;
    double Heading, error, startingPosition, initPitch;
    int direction;
    private final Timer m_timer = new Timer();

    DriveOntoCharge (Drivetrain drive, NavX gyro, int forward) {
        this.m_gyro = gyro;
        this.m_drive = drive;
        this.direction = forward;
    }

    @Override
    public void initialize() {
        this.Heading = m_gyro.getHeading();
        this.m_timer.reset();
    }

    @Override
    public void execute() {
        error = (m_gyro.getHeading() - Heading) * Constants.straightDriveP;
        m_drive.drive(direction * Constants.OntoChargeStationSpeed - error, direction * Constants.OntoChargeStationSpeed + error);
    }

    @Override
    public boolean isFinished() {
        if (Math.abs(m_gyro.getPitch()) > 10) { 
            return true;
        }
        else {
            return false;
        }
    }
}