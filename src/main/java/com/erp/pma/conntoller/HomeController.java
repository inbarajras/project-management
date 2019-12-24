package com.erp.pma.conntoller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.erp.pma.dao.EmployeeRepository;
import com.erp.pma.dao.ProjectRepository;
import com.erp.pma.dto.EmployeeProject;
import com.erp.pma.dto.ProjectStage;
import com.erp.pma.entities.Employee;
import com.erp.pma.entities.Project;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	ProjectRepository projectRepo;
	
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping
	public String dashboard(Model model,@RequestParam(value="username",required = false)String username,@RequestParam(value="pass",required = false) String pass) throws JsonProcessingException {
//		if(Objects.nonNull(username)&&Objects.nonNull(pass)) {
//			if(pass.equals(username+"login")){
		List<Project>projects=projectRepo.findAll();
		List<EmployeeProject>employeeProjectListCount=empRepo.getEmployeeProjectCnt();
		List<ProjectStage> projectStatus = projectRepo.getProjectStageCount();
		ObjectMapper projectStatusData = new ObjectMapper();
		String jsonData=projectStatusData.writeValueAsString(projectStatus);
		model.addAttribute("projectStatus", jsonData);
		model.addAttribute("projects",projects);
		model.addAttribute("employeeProjectList",employeeProjectListCount);
		return "main/home.html";
//		}else {
//			return "redirect:/home/login";	
//		}}else {
//			return "redirect:/home/login";
//		}
	}
	
	@GetMapping("/login")
	public String login() {
		return "main/login-school";
		
	}
	
	@GetMapping("/projectlist")
	public String displayProjects(Model model) {
		List<Project>projects=projectRepo.findAll();
		model.addAttribute("projects",projects);
		return "projects/list-projects.html";
		
		
	}
	

	@GetMapping("/newprj")
	public String displayIndex(Model model) {
		List<Employee> allEmployees = empRepo.findAll();
		model.addAttribute("project" ,new Project());
		model.addAttribute("allEmployees" ,allEmployees);
		return "projects/new-project.html";
		
	}
	
	@PostMapping("/saveprj")
	public String save(Project project,@RequestParam List<Long> employees) {
		projectRepo.save(project);
		return "redirect:/newprj";
		
	}

	@GetMapping("/emplist")
	public String displayEmployee(Model model) {
		List<Employee>employees=empRepo.findAll();
		model.addAttribute("employees",employees);
		return "employee/list-employees.html";
		
		
	}
	
	@GetMapping("/newemp")
	public String register(Model model) {
		model.addAttribute("employee",new Employee());
		return "employee/employee-register.html";
		
	}
	
	@PostMapping("/saveemp")
	public String save(Employee employee) {
		empRepo.save(employee);
		return "redirect:/newemp";
		
	}
	
}
