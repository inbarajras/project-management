package com.erp.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.erp.pma.dto.EmployeeProject;
import com.erp.pma.entities.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long>{
	
	@Override
	public List<Employee> findAll();
	
	@Query(nativeQuery = true,value = "SELECT e.first_name as firstName,e.last_name as lastName,Count(pe.project_id) as projectCount FROM EMPLOYEE e left join project_employee pe on e.employee_id=pe.employee_id group by e.first_name,e.last_name Order by 3 DESC")
	public List<EmployeeProject> getEmployeeProjectCnt();

}
