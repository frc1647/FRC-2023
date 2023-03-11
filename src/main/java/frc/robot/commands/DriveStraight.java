package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;

//import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
//import edu.wpi.first.wpilibj2.command.PIDCommand;


import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.NavX;

public class DriveStraight extends CommandBase {

    NavX m_gyro;
    Drivetrain m_drive;
    double Heading;
    double error;
    private final Timer m_timer = new Timer();

    DriveStraight (Drivetrain drive, NavX gyro) {
        this.m_gyro = gyro;
        this.m_drive = drive;
    }

    @Override
    public void initialize() {
        this.Heading = m_gyro.getHeading();
        this.m_timer.reset();
    }

    @Override
    public void execute() {
        error = (m_gyro.getHeading() - Heading) * Constants.straightDriveP;
        m_drive.drive(Constants.straightDrivePower - error, Constants.straightDrivePower + error);
    }

    //@Override
    //public boolean isFinished() {
        /*if (m_timer.get() > Constants.straightDriveTime) {
            return true;
        }
        else {
            return false;
        }*/
    //}
}