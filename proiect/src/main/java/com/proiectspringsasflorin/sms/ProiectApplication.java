package com.proiectspringsasflorin.sms;

import com.proiectspringsasflorin.sms.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.proiectspringsasflorin.sms.repository.PatientRepository;

@SpringBootApplication
public class ProiectApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ProiectApplication.class, args);
	}

	@Autowired
	private PatientRepository patientRepository;

	@Override
	public void run(String... args) throws Exception {

	}

}
