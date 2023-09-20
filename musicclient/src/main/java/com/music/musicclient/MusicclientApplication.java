package com.music.musicclient;

import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(exclude= {SecurityAutoConfiguration.class })
public class MusicclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicclientApplication.class, args);
	}

}
