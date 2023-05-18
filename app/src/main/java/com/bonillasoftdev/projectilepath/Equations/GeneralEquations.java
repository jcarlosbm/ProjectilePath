package com.bonillasoftdev.projectilepath.Equations;

import com.bonillasoftdev.projectilepath.Model.DataModel;
import com.bonillasoftdev.projectilepath.Model.GeneralResultModel;

public class GeneralEquations {
    private DataModel data;
    private GeneralResultModel result;

    public GeneralEquations(DataModel data){
        this.data = data;
        result = new GeneralResultModel();
    }

    public double calculateVelocityComponentInX(){
        double radiansAngles = Math.toRadians(data.getInitialAngle());
        return data.getInitialVelocity() * Math.cos(radiansAngles);
    }

    public double calculateVelocityComponentInY(){
        double radiansAngles = Math.toRadians(data.getInitialAngle());
        return data.getInitialVelocity() * Math.sin(radiansAngles);
    }

    public double calculateFlightTime(double velocityComponentInY){
        return  (2 * (velocityComponentInY))/data.getGravity();
    }

    public double calculateMaxRange(){
        double radiansAngles = Math.toRadians(data.getInitialAngle());
        return  ((data.getInitialVelocity() * data.getInitialVelocity())* (Math.sin(2 * radiansAngles)) / data.getGravity());
    }

    public double calculateMaxHeight(){
        double radiansAngles = Math.toRadians(data.getInitialAngle());
        return  ((data.getInitialVelocity() * data.getInitialVelocity()) * (Math.sin(radiansAngles) * Math.sin(radiansAngles)))/(2 * data.getGravity());
    }

    public double calculateRiseTime(){
        double radiansAngles = Math.toRadians(data.getInitialAngle());
        return  (data.getInitialVelocity() * Math.sin(radiansAngles))/ data.getGravity();
    }

    public GeneralResultModel getGeneralResultModel(){

        GeneralResultModel result = new GeneralResultModel();
        result.setVelocityComponentInX(calculateVelocityComponentInX());
        result.setVelocityComponentInY(calculateVelocityComponentInY());
        result.setFlightTime(calculateFlightTime(calculateVelocityComponentInY()));
        result.setMaximumRange(calculateMaxRange());
        result.setMaximumHeight(calculateMaxHeight());
        result.setRiseTime(calculateRiseTime());

        return  result;

    }

}
