package com.example.demo;

import java.sql.Time;
import java.util.UUID;
import java.sql.Timestamp;

public class detectedMobileStation {
    //DMS POJO
    UUID id;
    float lastKownX, lastKownY;
    double dis;
    Timestamp timeStamp;

    public detectedMobileStation (float x, float y, UUID id, Timestamp timeStamp, double dis){
        this.id = id;
        this.timeStamp = timeStamp;
        this.lastKownX = x;
        this.lastKownY = y;
        this.dis = dis;
    }

    public UUID getID(){
        return id;
    }
    public float getlastKownX(){
        return lastKownX;
    }
    public float getlastKownY(){
        return lastKownY;
    }
    public Timestamp gettimestamp(){
        return timeStamp;
    }

    public double getdis(){
        return dis;
    }
}
