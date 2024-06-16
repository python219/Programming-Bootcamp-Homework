// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.function.Supplier;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkLowLevel.MotorType;

import frc.robot.Constants.DrivetrainConstants;
import frc.robot.RobotMap;

public class Drivetrain extends SubsystemBase {
  private CANSparkMax frontRight = new CANSparkMax(RobotMap.frontRight, MotorType.kBrushless);
  private CANSparkMax frontLeft = new CANSparkMax(RobotMap.frontLeft, MotorType.kBrushless);
  private CANSparkMax backRight = new CANSparkMax(RobotMap.backLeft, MotorType.kBrushless);
  private CANSparkMax backLeft = new CANSparkMax(RobotMap.backRight, MotorType.kBrushless);

  private SparkPIDController rightPID = frontRight.getPIDController();
  private SparkPIDController leftPID = frontLeft.getPIDController();

  private RelativeEncoder rightEncoder = frontRight.getEncoder();
  private RelativeEncoder leftEncoder = frontLeft.getEncoder();

  private DifferentialDriveKinematics differentialDriveKinematics = new DifferentialDriveKinematics(DrivetrainConstants.TrackWidth);
  
  /** Creates a new Drivetrain. */
  public Drivetrain() {
    backRight.follow(frontRight);
    backLeft.follow(frontLeft);

    frontRight.setInverted(true);
    frontLeft.setInverted(false);

    leftPID.setP(DrivetrainConstants.P);
    leftPID.setI(DrivetrainConstants.I);
    leftPID.setD(DrivetrainConstants.D);
    leftPID.setFF(DrivetrainConstants.FF);

    rightPID.setP(DrivetrainConstants.P);
    rightPID.setI(DrivetrainConstants.I);
    rightPID.setD(DrivetrainConstants.D);
    rightPID.setFF(DrivetrainConstants.FF);

    rightEncoder.setPositionConversionFactor(DrivetrainConstants.PositionConversionFactor);
    leftEncoder.setPositionConversionFactor(DrivetrainConstants.PositionConversionFactor);

    rightEncoder.setVelocityConversionFactor(DrivetrainConstants.VelocityConversionFactor);
    leftEncoder.setVelocityConversionFactor(DrivetrainConstants.VelocityConversionFactor);
  }

  public void setSpeeds(ChassisSpeeds speeds) {
    DifferentialDriveWheelSpeeds wheelSpeeds = differentialDriveKinematics.toWheelSpeeds(speeds);
    rightPID.setReference(wheelSpeeds.rightMetersPerSecond, ControlType.kVelocity);
    leftPID.setReference(wheelSpeeds.leftMetersPerSecond, ControlType.kVelocity);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
