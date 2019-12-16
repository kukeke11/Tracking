package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.Math.*;

public class SetUp {
    public List<BaseStation> baseStations = new ArrayList<>();
    public List<MobileStation> mobileStations = new ArrayList<>();

    Random rand = new Random();

    int min = 0;
    int max = 200;

    public void giveRandomLocations()
    {
        // Give random locations to the stations for testing purposes
        for(int i = 1;  i < 100; i++){
            float bsX, bsY, bsdRIM;
            float msX, msY;
            msX = rand.nextFloat() * (max - min);
            msY = rand.nextFloat() * (max - min);

            bsX = rand.nextFloat() * (max - min);
            bsY = rand.nextFloat() * (max - min);

            bsdRIM = rand.nextFloat() * (4 - 0);

            BaseStation BS = new BaseStation(bsX, bsY, bsdRIM);
            MobileStation MS = new MobileStation(msX, msY);
            mobileStations.add(MS);
            baseStations.add(BS);
        }
    }
// Check if MS is withing BS detection radius
    public double radiusDetection(BaseStation bs,
                                MobileStation ms){
        double dis;
        dis = Math.sqrt((bs.x - ms.lastKownX)*(bs.x - ms.lastKownX) + (bs.y-ms.lastKownY)*(bs.y-ms.lastKownY));
        return dis;
    }
// Iterates every BS through MS list
    public void checkLocations(){
        for (BaseStation bs : baseStations){
            for (MobileStation ms : mobileStations){
                double dis;
                dis = radiusDetection(bs, ms);
                checkDetected(bs, ms, dis);
            }
        }
    }
    // Main check to find out if a station is already added or not
    public void checkDetected(BaseStation bs, MobileStation ms, double dis){
        boolean detected = false;
        for (detectedMobileStation dMS:bs.msDetected) {
            //Check if station exists in BS detected list
            if(!bs.msDetected.isEmpty()){
                if(dMS.id == ms.id){
                    //Check if distance already matches to the one before
                    if(dMS.dis == dis){
                        //if distance matches but and positions are the same do nothing
                        if(dMS.lastKownX == ms.lastKownX && dMS.lastKownY == ms.lastKownY){
                            detected = true;
                            break;
                        }
                        //if distance matches but locations is different update locations and TimeStamp
                        else{
                            dMS.timeStamp = new Timestamp(System.currentTimeMillis());
                            dMS.lastKownY = ms.lastKownY;
                            dMS.lastKownX = ms.lastKownX;
                            detected = true;
                            break;
                        }
                    }
                    //If none detected adds a MS to BS detected list
                    else{
                        if(dis < bs.detectionRadiusInMeters){
                            dMS.lastKownY = ms.lastKownY;
                            dMS.lastKownX = ms.lastKownX;
                            dMS.dis = dis;
                            dMS.timeStamp = new Timestamp(System.currentTimeMillis());
                            detected = true;
                            break;
                        }
                        //IF not in range anymore remove.
                        else{
                            bs.msDetected.remove(dMS);
                            detected = true;
                            break;
                        }
                    }
                }
                // if none found change bool so a new one can be added
                else{
                    detected = false;
                }
            }
        }
        // if in range add
        if(dis < bs.detectionRadiusInMeters && !detected){
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            detectedMobileStation ndMS = new detectedMobileStation(ms.lastKownX, ms.lastKownY,ms.id, timestamp, dis);
            bs.msDetected.add(ndMS);
        }
    }
    // just a test
    public void testNumers(){
        float bsX, bsY, bsdRIM;
        float msX, msY;
        msX = rand.nextFloat() * (max - min);
        msY = rand.nextFloat() * (max - min);

        bsX = rand.nextFloat() * (max - min);
        bsY = rand.nextFloat() * (max - min);

        bsdRIM = rand.nextFloat() * (4 - 0);

        double dis = Math.sqrt((bsX - msX)*(bsX - msX) + (bsY - msY)*(bsY - msY));

        System.out.println("bsX:"+ bsX);
        System.out.println("bsY:"+ bsY);
        System.out.println("msX:"+ msX);
        System.out.println("msY:"+ msY);
        System.out.println("bsRad:"+ bsdRIM);
        System.out.println("Distance:"+ dis);

    }

}
