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
    double Heading, error, setPointDistance, power, startingPosition;
    boolean forwards;

    DriveStraight (Drivetrain drive, NavX gyro, double distance, double speed) {
        this.m_gyro = gyro;
        this.m_drive = drive;
        this.setPointDistance = distance;
        this.power = speed;
    }

    @Override
    public void initialize() {
        this.Heading = m_gyro.getHeading();
        this.startingPosition = m_drive.getLeftPosition();
        this.forwards = m_drive.getLeftPosition() < setPointDistance + startingPosition;
    }

    @Override
    public void execute() {
        if (Math.abs(Heading - m_gyro.getHeading()) > 180) {
            error = (m_gyro.getHeading() + 360 - Heading) * Constants.straightDriveP;
        } else {
            error = (m_gyro.getHeading() - Heading) * Constants.straightDriveP;
        }
        if (forwards) {
            m_drive.drive(power - error, power + error);
        } else {
            m_drive.drive(-power - error, -power + error);
        }
    }

    //encoder 42 ticks per rev, wheel circumference 1.57 feet, 65.9 * distance in feet
    @Override
    public boolean isFinished() {
        if (forwards && m_drive.getLeftPosition() >= setPointDistance + startingPosition) { 
            return true;
        } else if (forwards == false && m_drive.getLeftPosition() <= setPointDistance + startingPosition) {
            return true;
        }
        else {
            return false;
        }
    }
}