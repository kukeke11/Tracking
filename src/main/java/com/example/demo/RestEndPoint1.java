package com.example.demo;

import stations.BaseStation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import service.Calculate;
import service.Logic;
import stations.MobileStation;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class RestEndPoint1 {

	public static void main(String[] args) {

		List<BaseStation> baseStations = new ArrayList<>();
		List<MobileStation> mobileStations = new ArrayList<>();

		SpringApplication.run(RestEndPoint1.class, args);
		SetUp setUp = new SetUp();
		Logic logic = new Logic();
		Calculate calc = new Calculate();

		Messages message = new Messages();

		setUp.giveRandomLocations(baseStations, mobileStations);
		logic.iterate(baseStations, mobileStations);
		for (BaseStation bs: baseStations)
		{
			if(!bs.getMsDetected().isEmpty()){
				message.detectionMessage(bs);
			}
		}
		for (MobileStation ms: mobileStations
			 ) {
			logic.handleTriangulation(ms, baseStations);
		}

	}

}
