package com.example.demo;

public class Messages {
//Print messages
    public void detectionMessage(BaseStation bs){
        System.out.println("Base Station ID:"+ bs.id + "report:");
        for (detectedMobileStation dms: bs.msDetected) {
            System.out.println("Detected Mobile Station ID: "+ dms.id + " distance: " + dms.dis + " meters TimeStamp:"+ dms.timeStamp);
        }
    }
}
