package com.ty.ams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ty.ams.serviceimp.EmailSenderService;

@SpringBootApplication(scanBasePackages = "com.ty.ams")
public class AmsApplication {
	@Autowired
	private EmailSenderService senderService;

	public static void main(String[] args) {
		SpringApplication.run(AmsApplication.class, args);
	}
}
