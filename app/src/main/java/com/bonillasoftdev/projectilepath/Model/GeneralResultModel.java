package com.bonillasoftdev.projectilepath.Model;

public class GeneralResultModel {
    private double velocityComponentInX;
    private double velocityComponentInY;
    private double FlightTime;
    private double MaximumRange;
    private double MaximumHeight;
    private double RiseTime;

    public void setVelocityComponentInX(double velocityComponentInX) {
        this.velocityComponentInX = velocityComponentInX;
    }

    public void setVelocityComponentInY(double velocityComponentInY) {
        this.velocityComponentInY = velocityComponentInY;
    }

    public void setFlightTime(double flightTime) {
        FlightTime = flightTime;
    }

    public void setMaximumRange(double maximumRange) {
        MaximumRange = maximumRange;
    }

    public void setMaximumHeight(double maximumHeight) {
        MaximumHeight = maximumHeight;
    }

    public void setRiseTime(double riseTime) {
        RiseTime = riseTime;
    }

    public double getVelocityComponentInX() {
        return velocityComponentInX;
    }

    public double getVelocityComponentInY() {
        return velocityComponentInY;
    }

    public double getFlightTime() {
        return FlightTime;
    }

    public double getMaximumRange() {
        return MaximumRange;
    }

    public double getMaximumHeight() {
        return MaximumHeight;
    }

    public double getRiseTime() {
        return RiseTime;
    }
}
