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
    boolean isLimelight = true;
    double xLimelight;
    double yLimelight;
    double areaLimelight;

    public LimeLight(RobotContainer robot){
        this.robotContainer = robot;

        try {
            NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
            this.tx = table.getEntry("tx"); //x cor (pointers)
            this.ty = table.getEntry("ty"); //y cor 
            this.ta = table.getEntry("ta"); //area of target
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
      
            //Limelight post to smart dashboard periodically
            SmartDashboard.putNumber("LimelightX", xLimelight);
            SmartDashboard.putNumber("LimelightY", yLimelight);
            SmartDashboard.putNumber("LimelightArea", areaLimelight);
        }
    }

    public double getXcor(){
        return xLimelight;
    }

    public double getArea(){
        return areaLimelight;
    }
}

