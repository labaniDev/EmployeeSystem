package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Status;
import com.example.demo.repository.EmployeeRepo;
@Service
public class EmployeeService {
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	EmployeeRepo employeeRepo;
	@Autowired
	PasswordEncoder encoder;
	private static final long EXPIRE_TOKEN_AFTER_MINUTES = 30;
	 public static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);
	 @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED) 
	 
	 public Boolean existsByEmail(String email) {
		 return employeeRepo.existsByEmail(email);
	 }
	public void createEmployee(EmployeeDTO employeeDTO) {
		 try {
			 LOGGER.debug("Inside Create Employee:"+employeeDTO.toString());
		 
	    Employee employee=modelMapper.map(employeeDTO, Employee.class);
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		  LocalDateTime now = LocalDateTime.now();  
		  employee.setStatus(Status.active);
		  employee.setCreated_on(dtf.format(now));
		  employee.setPassword(encoder.encode(employeeDTO.getPassword()));
		  employeeRepo.save(employee);
	}catch(Exception ex) {
		ex.printStackTrace();
		LOGGER.error("Exception in addEmployee:"+ex.getMessage());
	}
	 }
	 
	 public Employee createLogin(String email,String password) {
		 try {
			 LOGGER.debug("Inside Login:");
		 Employee employee=employeeRepo.findByEmail(email);
		 if(employee!=null && employee.getPassword().equals(password)) {
			 return employee;
		 }
	 }catch(Exception ex) {
		 ex.printStackTrace();
		 LOGGER.error("Exception in login:"+ex.getMessage());
	 }
		return null;
}
	 public List<EmployeeDTO> getAllEmployees(){
		 try {
			 List<Employee> employeeList=employeeRepo.findAll();
			 List<EmployeeDTO> employeedtoList=modelMapper.map(employeeList,new TypeToken<List<EmployeeDTO>>() {}.getType() );
			return employeedtoList; 
		 }catch(Exception ex) {
			 ex.printStackTrace();
			 LOGGER.error("Exception in get All Employees:"+ex.getMessage());
		 }
		return null;
		
	 }
	 public void updateEmployee(EmployeeDTO employeeDTO) {
			try {
				LOGGER.debug("Inside Update Employee:"+employeeDTO.toString());
				Optional<Employee> empOptional=employeeRepo.findById(employeeDTO.getId());
				if(empOptional.isPresent()) {
					Employee emp=empOptional.get();
					 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
					  LocalDateTime now = LocalDateTime.now();  
					  emp.setCreated_on(dtf.format(now));
					  emp.setEmail(employeeDTO.getEmail());
					  emp.setPassword(encoder.encode(employeeDTO.getPassword()));
					  emp.setPhone(employeeDTO.getPhone());
					  emp.setName(employeeDTO.getName());
					  emp.setStatus(Status.active);
					  employeeRepo.save(emp);
				}
			}catch(Exception ex){
				ex.printStackTrace();
				LOGGER.error("Exception in update Employees:"+ex.getMessage());
			}
		}
}
