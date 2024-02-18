package com.example.demo.service;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepo;
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	EmployeeRepo employeeRepo;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) {
		
		Employee user=employeeRepo.findByEmail(email);
		
		return UserDetailsImpl.build(user);
	}

}
