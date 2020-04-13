package io.moresushant48.WhatStatus;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WhatStatusApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhatStatusApplication.class, args);
		
		File file = new File("\\storedfiles");
		try {
			Files.createDirectories(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
