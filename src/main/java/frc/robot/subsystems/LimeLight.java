package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

import java.util.function.DoubleSupplier;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LimeLight extends SubsystemBase{

    private RobotContainer robotContainer;

    NetworkTableEntry tx;
    NetworkTableEntry ty;
    NetworkTableEntry ta;
    NetworkTableEntry tvert;
    NetworkTableEntry camMode;
    NetworkTableEntry ledMode;
    boolean isLimelight = true;
    double xLimelight;
    double yLimelight;
    double areaLimelight;
    double verticalAreaLimelight;

    public LimeLight(RobotContainer robot){
        this.robotContainer = robot;

        try {
            NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
            tx = table.getEntry("tx"); //x cor (pointers)
            ty = table.getEntry("ty"); //y cor 
            ta = table.getEntry("ta"); //area of target
            tvert = table.getEntry("tvert");
            camMode = table.getEntry("camMode");
            ledMode = table.getEntry("ledMode");
        } catch (Exception e) {
            e.printStackTrace();
            this.isLimelight = false;
        }
    }

    public void LimeLightPeriodic() {
        if (isLimelight) {
            //Limelight read values periodically
            this.xLimelight = tx.getDouble(0.0);
            this.yLimelight = ty.getDouble(0.0);
            this.areaLimelight = ta.getDouble(0.0);
            this.verticalAreaLimelight = tvert.getDouble(0.0);
      
            //Limelight post to smart dashboard periodically
            SmartDashboard.putNumber("LimelightX", xLimelight);
            SmartDashboard.putNumber("LimelightY", yLimelight);
            SmartDashboard.putNumber("LimelightArea", areaLimelight);
            SmartDashboard.putNumber("LimelightVertArea", verticalAreaLimelight);

            // Limelight Modes
            //SmartDashboard.putNumber("LimeLightCamMode", camMode.getDouble(-0.1));
            //SmartDashboard.putNumber("LimeLightLEDMode", ledMode.getDouble(-0.1));
        }
    }

    public double getXcor(){
        return xLimelight;
    }

    public double getArea(){
        return areaLimelight;
    }

    public double getVerticalArea(){
        return verticalAreaLimelight;
    }

    public void setCamMode(int setting) {
        camMode.setInteger(setting);
        ledMode.setInteger(setting);
    }


}

