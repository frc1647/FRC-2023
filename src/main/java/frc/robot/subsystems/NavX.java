package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class NavX extends SubsystemBase {

  private static AHRS navX;
  //double Roll;
  double Pitch;
  double Heading;
  double Yaw;
  //Boolean isCalibrating;
  //Boolean isConnected;
  //double initialHeading;

  /** Creates a new NavX. */
  public NavX() {
    navX = new AHRS(SPI.Port.kMXP);
    navX.calibrate();
    /*ahrs = new AHRS(SerialPort.Port.kMXP); // Alternatives:  SPI.Port.kMXP, I2C.Port.kMXP or SerialPort.Port.kUSB */
    //initialHeading = navX.getCompassHeading();
  }

  @Override
  public void periodic() {
    //Roll = navX.getRoll();
    Pitch = navX.getPitch();
    Heading = navX.getYaw();// - initialHeading;
    SmartDashboard.putNumber("Pitch", Pitch);
    SmartDashboard.putNumber("Heading", Heading);
    //SmartDashboard.putBoolean("IsCalibrating", isCalibrating);
    //SmartDashboard.putBoolean("Connected", isConnected);
    //SmartDashboard.putNumber("Roll", Roll);
  }

  public void resetHeading() {
    navX.reset();
  }

  public double getHeading() {
    return Heading;
  }
 
  public double getPitch() {
    return Pitch;
  }
}
