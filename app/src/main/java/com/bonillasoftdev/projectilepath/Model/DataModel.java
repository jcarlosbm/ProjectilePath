package com.bonillasoftdev.projectilepath.Model;

public class DataModel {
    double initialVelocity;
    double initialAngle;
    double time;

    public DataModel(double initialVelocity, double initialAngle, double time){
        this.initialVelocity = initialVelocity;
        this.initialAngle = initialAngle;
        this.time = time;
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
}
