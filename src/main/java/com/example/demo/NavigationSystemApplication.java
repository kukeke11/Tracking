package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NavigationSystemApplication {

	public static void main(String[] args) {

		SpringApplication.run(NavigationSystemApplication.class, args);
		SetUp setUp = new SetUp();
		Messages message = new Messages();

		//setUp.testNumers();
		setUp.giveRandomLocations();
		setUp.checkLocations();

		for (BaseStation bs: setUp.baseStations)
		{
			if(!bs.msDetected.isEmpty()){
				message.detectionMessage(bs);
			}
		}
	}

}
