package com.example.demo;

import stations.BaseStation;
import stations.MobileStation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SetUp {
    Random rand = new Random();

    int min = 0;
    int max = 50;

    public void giveRandomLocations(List<BaseStation> baseStations,List<MobileStation> mobileStations)
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
            System.out.println("Generating Positions...."+ i);
            BaseStation BS = new BaseStation(bsX, bsY, bsdRIM);
            MobileStation MS = new MobileStation(msX, msY);
            mobileStations.add(MS);
            baseStations.add(BS);
        }
    }
}
