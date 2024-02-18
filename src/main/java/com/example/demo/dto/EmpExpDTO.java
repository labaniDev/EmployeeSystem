package com.example.demo.dto;

import java.util.Set;



import lombok.Data;
@Data
public class EmpExpDTO {
	private Long employeeId;
	private Set<ExperienceDTO> experiences;

}
