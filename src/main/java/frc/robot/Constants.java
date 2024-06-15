// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int DriverControllerPort = 0;
  }
  public static class DrivetrainConstants {
    public static final int TrackWidth = 1;
    public static final double P = 0.25;
    public static final double I = 0.0;
    public static final double D = 0.0;
    public static final double FF = 0.0;
    // Wheel width * pi / gear ratio
    public static final double PositionConversionFactor = 0.1 * Math.PI / 10.0;
    public static final double VelocityConversionFactor = PositionConversionFactor / 60;
    public static final double ForwardSpeed = 1;
    public static final double AngularSpeed = 1;
  }
}
