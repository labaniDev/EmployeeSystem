package com.example.demo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EmpExpDTO;
import com.example.demo.dto.ExperienceDTO;
import com.example.demo.service.ExperienceService;
@RestController
@RequestMapping("/api/exp")
public class ExperienceController {
	
	@Autowired
	ExperienceService expService;
	
	@PostMapping("/addExp")
	public ResponseEntity<ExperienceDTO> addExperience(@RequestBody EmpExpDTO empExpDTO) {
		expService.addExperience(empExpDTO);
		return new ResponseEntity<ExperienceDTO>(HttpStatus.CREATED);
	}
	@PutMapping("/updateExp")
	public ResponseEntity<ExperienceDTO> updateExperience(@RequestBody EmpExpDTO empExpDTO){
		expService.updateExperience(empExpDTO);
		return new ResponseEntity<ExperienceDTO>(HttpStatus.OK);
	}
	@GetMapping("/getAllExperience")
	public List<ExperienceDTO> getAllExperience(){
		return expService.getAllExperience();
	}
	

}
