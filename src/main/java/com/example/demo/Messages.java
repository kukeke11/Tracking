package com.example.demo;

import stations.BaseStation;
import stations.DetectedMobileStation;

public class Messages {
//Print messages
    public void detectionMessage(BaseStation bs){
        System.out.println("Base Station ID:"+ bs.getID() + "report:");
        for (DetectedMobileStation dms: bs.getMsDetected()) {
            System.out.println("Detected Mobile Station ID: "+ dms.getID() + " distance: " + dms.getDis() + " meters TimeStamp:"+ dms.getTimeStamp());
        }
    }
}
