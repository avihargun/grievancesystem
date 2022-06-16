package com.simformsolutions.grievance;

import com.simformsolutions.grievance.controller.ComplainController;
import com.simformsolutions.grievance.service.ComplainService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class GrievanceApplication {

	public static void main(String[] args) {
		new File(ComplainService.uploadDirectory).mkdir();
		SpringApplication.run(GrievanceApplication.class, args);
	}

}
