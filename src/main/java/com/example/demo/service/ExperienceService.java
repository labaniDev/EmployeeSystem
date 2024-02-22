package com.example.demo.service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.EmpExpDTO;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.ExperienceDTO;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Experience;
import com.example.demo.repository.EmployeeRepo;
import com.example.demo.repository.ExperienceRepo;

@Service
public class ExperienceService {
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	ExperienceRepo experienceRepo;
	@Autowired
	EmployeeRepo employeeRepo;
	public static final Logger LOGGER = LoggerFactory.getLogger(ExperienceService.class);
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
	public void addExperience(EmpExpDTO empExpDTO) {
		try {
	
			for(ExperienceDTO experienceDTO:empExpDTO.getExperiences()) {
				LOGGER.debug("Inside AddExperience:"+empExpDTO.toString());
				Optional<Employee> employeeOptional=employeeRepo.findById(empExpDTO.getEmployeeId());
				if(employeeOptional.isPresent()) {
					Employee emp=employeeOptional.get();
					Experience exp=new Experience();
					 String pattern = "yyyy-MM-dd";
			            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			            String dateStr = simpleDateFormat.format(new Date());
			            exp.setDate_of_joining(dateStr);
			            exp.setLast_date(dateStr);
			            exp.setCompany_name(experienceDTO.getCompany_name());
			            exp.setRole(experienceDTO.getRole());
			           
					  exp.setEmployee(emp);
					  experienceRepo.save(exp);
				
			}
			
		}
		
		}catch(Exception ex) {
			ex.printStackTrace();
			LOGGER.error("Exception in addExperience"+ex.getMessage());
		}
	}
	
	public void updateExperience(EmpExpDTO empExpDTO) {
		try {
			
			LOGGER.debug("Inside updateExperience:");
			for(ExperienceDTO experienceDTO:empExpDTO.getExperiences()) {
		Optional<Experience> expOptional=experienceRepo.findById(experienceDTO.getId());
		Optional<Employee> empOptional=employeeRepo.findById(empExpDTO.getEmployeeId());
		if(expOptional.isPresent()&&empOptional.isPresent()) {
			Experience exp=expOptional.get();
			Employee emp=empOptional.get();
			String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String dateStr = simpleDateFormat.format(new Date());
            exp.setDate_of_joining(dateStr);
            exp.setLast_date(dateStr);
            exp.setCompany_name(experienceDTO.getCompany_name());
            exp.setRole(experienceDTO.getRole());
			exp.setEmployee(emp);
			experienceRepo.save(exp);
		}
			}
	}catch(Exception ex) {
		ex.printStackTrace();
		LOGGER.error("Exception in updateExperience"+ex.getMessage());
	}
	}
	
	public List<ExperienceDTO> getAllExperience() {
	    return experienceRepo.findAll().stream()
	            .map(exp-> modelMapper.map(exp, ExperienceDTO.class))
	            .collect(Collectors.toList());
	}
	
	

}
