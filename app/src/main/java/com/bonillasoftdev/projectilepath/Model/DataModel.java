package com.bonillasoftdev.projectilepath.Model;

public class DataModel {
    private double initialVelocity;
    private double initialAngle;
    private double time;
    private double gravity;

    public DataModel(double initialVelocity, double initialAngle, double time, double gravity){
        this.initialVelocity = initialVelocity;
        this.initialAngle = initialAngle;
        this.time = time;
        this.gravity = gravity;
    }

    public double getInitialVelocity() {
        return initialVelocity;
    }

    public double getInitialAngle() {
        return initialAngle;
    }

    public double getTime() {
        return time;
    }
    public double getGravity() {
        return gravity;
    }
}
