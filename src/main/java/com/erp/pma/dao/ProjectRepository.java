package com.erp.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.erp.pma.dto.ProjectStage;
import com.erp.pma.entities.Project;

public interface ProjectRepository extends CrudRepository<Project, Long>{

	@Override
	public List<Project> findAll();
	
	@Query(nativeQuery = true,value = "Select stage as label ,Count(*) as value " + 
			"from project " + 
			"group by label")
	public List<ProjectStage> getProjectStageCount();
}
