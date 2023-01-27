package com.proiectspringsasflorin.sms.service.impl;

import java.util.List;

import com.proiectspringsasflorin.sms.entity.Patient;
import com.proiectspringsasflorin.sms.repository.PatientRepository;
import com.proiectspringsasflorin.sms.service.PatientService;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {

	private PatientRepository patientRepository;
	
	public PatientServiceImpl(PatientRepository patientRepository) {
		super();
		this.patientRepository = patientRepository;
	}

	@Override
	public List<Patient> getAllPatients() {
		return patientRepository.findAll();
	}

	@Override
	public Patient savePatient(Patient patient) {
		return patientRepository.save(patient);
	}

	@Override
	public Patient getPatientById(Long id) {
		return patientRepository.findById(id).get();

	}

	@Override
	public Patient updatePatient(Patient patient) {
		return patientRepository.save(patient);
	}

	@Override
	public void deletePatientById(Long id) {
		patientRepository.deleteById(id);
	}


	public List<Patient> findByKeyword(String keyword){
		return patientRepository.findByKeyword(keyword);
	}
}
