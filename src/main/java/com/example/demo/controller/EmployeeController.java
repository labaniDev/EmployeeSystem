package com.example.demo.controller;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.MessageResponse;
import com.example.demo.entity.UserInfoResponse;
import com.example.demo.security.config.JwtUtils;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.UserDetailsImpl;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = "*")
public class EmployeeController {
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	JwtUtils jwtUtils;
	
	@PostMapping("/signup")
	public ResponseEntity<MessageResponse> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
		if(employeeService.existsByEmail(employeeDTO.getEmail())) {
			 return ResponseEntity.ok().body(new MessageResponse("Error: Email is already taken!"));
		}
		employeeService.createEmployee(employeeDTO);
		return ResponseEntity.ok(new MessageResponse("Employee Registered Successfully"));
	}
	@PostMapping("/signin")
	public ResponseEntity<UserInfoResponse> authenticateUser(@Valid@RequestBody EmployeeDTO employeeDTO) {
		
		Authentication authentication = authenticationManager
		        .authenticate(new UsernamePasswordAuthenticationToken(employeeDTO.getEmail(),employeeDTO.getPassword()));	
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		String jwt = jwtUtils.generateJwtCookie(userDetails);		
		return ResponseEntity.ok().body( new UserInfoResponse(userDetails.getId(),
                userDetails.getUsername(),jwt));
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
//	@PostMapping("/login")
//	public String createLogin(@RequestParam(value="email",required = false) String email,@RequestParam(value="password",required = false) String Password) {
//		employeeService.createLogin(email, Password);
//		return "Employee login's Successfully";
//	}
	
	
	

}
