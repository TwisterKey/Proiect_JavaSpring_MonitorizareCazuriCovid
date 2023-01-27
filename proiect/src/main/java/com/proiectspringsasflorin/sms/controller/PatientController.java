package com.proiectspringsasflorin.sms.controller;

import com.proiectspringsasflorin.sms.entity.Patient;
import com.proiectspringsasflorin.sms.service.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//indică faptul că această clasă este un controler Spring MVC.
public class PatientController {

	private PatientService patientService;
	//o variabilă membru de tip PatientService care va fi utilizată
	// pentru a apela metodele din serviciul de pacienți.

	public PatientController(PatientService patientService) {
		super();
		this.patientService = patientService;
	}
	

	@GetMapping("/patients")
	//această anotare indică faptul că această metodă va răspunde la
	// cererile GET cu url-ul "/patients"
	public String listPatients(Model model, String keyword) {
		if(keyword!=null){
			model.addAttribute("patients", patientService.findByKeyword(keyword));
		}
		else{
			model.addAttribute("patients", patientService.getAllPatients());
		}
		return "patients";
	}

	@GetMapping("/patients/new")
	public String createPatientForm(Model model) {

		Patient patient = new Patient();
		model.addAttribute("patient", patient);

		return "create_patient";
	}

	@PostMapping("/patients")
	public String savePatient(@ModelAttribute("patient") Patient patient) {
		patientService.savePatient(patient);
		return "redirect:/patients";
	}

	@GetMapping("/patients/edit/{id}")
	public String editPatientForm(@PathVariable Long id, Model model) {
		model.addAttribute("patient", patientService.getPatientById(id));
		return "edit_patient";
	}

	@PostMapping("/patients/{id}")
	public String updatePatient(@PathVariable Long id, @ModelAttribute("patient") Patient patient, Model model) {
		Patient existingPatient = patientService.getPatientById(id);
		existingPatient.setId(id);
		existingPatient.setName(patient.getName());
		existingPatient.setPnumber(patient.getPnumber());
		existingPatient.setDetails(patient.getDetails());
		patientService.updatePatient(existingPatient);
		return "redirect:/patients";
	}
	
	@GetMapping("/patients/{id}")
	public String deletePatient(@PathVariable Long id) {
		patientService.deletePatientById(id);
		return "redirect:/patients";
	}
}

