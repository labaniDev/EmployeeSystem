package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExperienceDTO {
	
	private Long id;
	private String company_name;
	private String role;
	private String date_of_joining;
	private String last_date;
	private Long employeeId;

}
