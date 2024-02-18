package com.example.demo.entity;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name= "experience")
public class Experience {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String company_name;
	private String role;
	private String date_of_joining;
	private String last_date;
	
	@OneToOne
	@JoinColumn(name="employee_id",referencedColumnName="id")
	private Employee employee;

}
