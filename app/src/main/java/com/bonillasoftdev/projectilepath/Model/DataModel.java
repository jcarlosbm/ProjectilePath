package com.bonillasoftdev.projectilepath.Model;

import java.io.Serializable;

public class DataModel implements Serializable {
    private double initialVelocity;
    private double initialAngle;
    private double time;
    private double gravity;
    String name;

    public DataModel(String name, double initialVelocity, double initialAngle, double gravity){
        this.initialVelocity = initialVelocity;
        this.initialAngle = initialAngle;
        this.gravity = gravity;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setTime(double time) {
        this.time = time;
    }
}
