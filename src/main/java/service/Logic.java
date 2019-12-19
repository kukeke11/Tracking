package service;

import stations.BaseStation;
import stations.DetectedByBaseStation;
import stations.DetectedMobileStation;
import stations.MobileStation;

import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Logic {

    Calculate calculation = new Calculate();
    Scanner scan = new Scanner(System.in);


    public void iterate(List<BaseStation> baseStations,List<MobileStation> mobileStations){
        for (BaseStation bs : baseStations){
            for (MobileStation ms : mobileStations){
                double dis;
                dis = calculation.radiusDetection(bs, ms);
                checkDetected(bs, ms, dis);
            }
        }
    }

    public void handleTriangulation(MobileStation ms, List<BaseStation> baseStations){
        float x1 = 0, x2 = 0, x3 = 0, y1 = 0, y2 = 0, y3 = 0;
        double dist1 = 0 , dist2 = 0, dist3 = 0;
        if(ms.getDetectedBy().size() > 2 ){
            for(int i = 0; i < 3; i++){
                DetectedByBaseStation dBBS = ms.getDetectedBy().get(i);
                System.out.println("Handling Triangulations"+ dBBS.getID());
                switch (i){
                    case 0:
                        x1 = dBBS.getX();
                        y1 = dBBS.getY();
                        dist1 = dBBS.getDis();
                    case 1:
                        x2 = dBBS.getX();
                        y2 = dBBS.getY();
                        dist2 = dBBS.getDis();
                    case 2:
                        x3 = dBBS.getX();
                        y3 = dBBS.getY();
                        dist3 = dBBS.getDis();
                        break;
                }
            }
            calculation.triangulatePosition(ms.getID(), x1,x2,x3,y1,y2,y3,dist1,dist2,dist3, baseStations);
        }
        else{
            System.out.println("None Detected");
        }
    }

    public void checkDetected(BaseStation bs, MobileStation ms, double dis){
        boolean detected = false;
        for (DetectedMobileStation dMS:bs.getMsDetected()) {
            //Check if station exists in BS detected list
            if(!bs.getMsDetected().isEmpty()){
                if(dMS.getID() == ms.getID()){
                    //Check if distance already matches to the one before
                    if(dMS.getDis() == dis){
                        detected = true;
                        break;
                    }
                    //If none detected adds a MS to BS detected list
                    else{
                        if(dis < bs.getDetectionRadiusInMeters()){
                            dMS.setDis(dis);
                            dMS.setTimeStamp(new Timestamp(System.currentTimeMillis()));
                        }
                        //IF not in range anymore remove.
                        else{
                            bs.getMsDetected().remove(dMS);
                        }
                        detected = true;
                        break;
                    }
                }
            }
        }
        // if in range add
        if(dis < bs.getDetectionRadiusInMeters() && !detected){
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            DetectedMobileStation ndMS = new DetectedMobileStation(ms.getID(), timestamp, dis, false);
            bs.getMsDetected().add(ndMS);
            detectedByLogic(bs, ms, dis);
            System.out.println("Adding "+ ms.getID() +" to detected");
        }
    }
    //Checks and adds BaseStations to MS detected by list so I can know which Base stations have detected that MobileStation
    private void detectedByLogic(BaseStation bs, MobileStation ms, double dis) {
        DetectedByBaseStation dBBS = new DetectedByBaseStation(bs.getID(),bs.getX(),bs.getY(), dis);
        if(!ms.getDetectedBy().isEmpty()){
            for ( DetectedByBaseStation dBS : ms.getDetectedBy()) {
                if (dBS.getID() != bs.getID()) {
                    ms.getDetectedBy().add(dBBS);
                }
                break;
            }
        }
        else{
            ms.getDetectedBy().add(dBBS);
        }
    }
}