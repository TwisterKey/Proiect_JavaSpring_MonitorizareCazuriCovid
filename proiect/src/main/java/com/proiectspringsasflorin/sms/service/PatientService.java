package com.proiectspringsasflorin.sms.service;

import java.util.List;

import com.proiectspringsasflorin.sms.entity.Patient;
import com.proiectspringsasflorin.sms.repository.PatientRepository;
import org.springframework.stereotype.Service;

@Service
public interface PatientService {

	List<Patient> getAllPatients();
	
	Patient savePatient(Patient patient);
	
	Patient getPatientById(Long id);
	
	Patient updatePatient(Patient patient);
	
	void deletePatientById(Long id);

	List<Patient> findByKeyword(String keyword);
}
