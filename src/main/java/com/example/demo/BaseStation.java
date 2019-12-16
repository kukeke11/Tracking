package com.example.demo;

import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

public class BaseStation {
    //BS POJO
    UUID id;
    float x, y;
    float detectionRadiusInMeters;
    List<detectedMobileStation> msDetected = new ArrayList<>();


    public BaseStation (float x, float y, float dRIM){
        id = UUID.randomUUID();
        this.x = x;
        this.y = y;
        this.detectionRadiusInMeters = dRIM;
    }

    public UUID getID(){
        return id;
    }
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public float getDetectionRadiusInMeters(){
        return detectionRadiusInMeters;
    }
}
