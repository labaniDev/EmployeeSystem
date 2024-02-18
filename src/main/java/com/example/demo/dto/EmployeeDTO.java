package com.example.demo.dto;

import java.util.Set;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.example.demo.entity.Experience;
import com.example.demo.entity.Status;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class EmployeeDTO {
	private Long id;
	private String name;
	private String phone;
	private String email;
	private String password;
	private String created_on;
	private Status status;
	private Set<Experience> experiences;

}
