package frc.robot.commands;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.NavX;

public class TurnDegrees extends PIDCommand{
    double setPoint;
    double measurementDouble;
    public TurnDegrees(Drivetrain drive, NavX gyro, double SetPointHeading){
        super(
            new PIDController(Constants.TurnDegreesP, Constants.TurnDegreesI, Constants.TurnDegreesD),
            gyro::getHeading,
            SetPointHeading,
            output -> drive.turnCounterClockwise(MathUtil.clamp(output, -Constants.turnDegreesMaxPower, Constants.turnDegreesMaxPower)),
            drive);
        setPoint = SetPointHeading;
    }

    //heading is a double -180 to 180
    @Override
    public void execute() {
        measurementDouble = m_measurement.getAsDouble();
        if (Math.abs(setPoint - measurementDouble) > 180){
            m_useOutput.accept(m_controller.calculate(measurementDouble, -360+setPoint)); //negative
        } else {
            m_useOutput.accept(m_controller.calculate(measurementDouble, setPoint)); //positive 
        }
    }
}