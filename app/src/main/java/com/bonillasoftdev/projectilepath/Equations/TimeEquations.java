package com.bonillasoftdev.projectilepath.Equations;

import com.bonillasoftdev.projectilepath.Model.DataModel;
import com.bonillasoftdev.projectilepath.Model.GeneralResultModel;
import com.bonillasoftdev.projectilepath.Model.TimeResultModel;

public class TimeEquations {
    DataModel data;
    GeneralResultModel resultModel;
    private TimeResultModel timeResultModel;

    public TimeEquations(DataModel data, GeneralResultModel resultModel ){
        this.data = data;
        this.resultModel = resultModel;
        timeResultModel = new TimeResultModel();
    }

    public double calculatePositionInX(double time){
        return resultModel.getVelocityComponentInX() * time;
    }

    public double calculatePositionInY(double time){
        return resultModel.getVelocityComponentInY() * time - (1.0/2.0) * data.getGravity() * time * time ;

    }

    public double calculateVerticalVelocity(double time){
        return resultModel.getVelocityComponentInY() - (data.getGravity() * time );
    }


    public double calculateHorizontalVelocity(){
        return resultModel.getVelocityComponentInX();
    }

    public double calculateSpeed(double horizontalVelocity, double verticalVelocity){
        return Math.sqrt((horizontalVelocity * horizontalVelocity) + (verticalVelocity * verticalVelocity));

    }

    public double calculateDistanceToTheOrigin(double posicionEnX, double posicionEnY){
        return Math.sqrt((posicionEnX * posicionEnX) + (posicionEnY * posicionEnY));

    }

    public double calculateAngle(double verticalVelocity, double horizontalVelocity){
        return Math.toDegrees(Math.atan(verticalVelocity / horizontalVelocity));
    }
    
    public TimeResultModel getTimeResultModel(){
        TimeResultModel timeResultModel = new TimeResultModel();
        timeResultModel.setCalculatePositionInX(calculatePositionInX(data.getTime()));
        timeResultModel.setCalculatePositionInY(calculatePositionInY(data.getTime()));
        timeResultModel.setCalculateVerticalVelocity(calculateVerticalVelocity(data.getTime()));
        timeResultModel.setCalculateHorizontalVelocity(calculateHorizontalVelocity());
        timeResultModel.setCalculateVerticalVelocity(calculateVerticalVelocity(data.getTime()));
        timeResultModel.setCalculateSpeed(calculateSpeed(calculateHorizontalVelocity(), calculateVerticalVelocity(data.getTime())));
        timeResultModel.setCalculateDistanceToTheOrigin(calculateDistanceToTheOrigin(calculatePositionInX(data.getTime()), calculatePositionInY(data.getTime())));
        timeResultModel.setCalculateAngle(calculateAngle(calculateVerticalVelocity(data.getTime()), calculateHorizontalVelocity()));

        return timeResultModel;
    }

}
