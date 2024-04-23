package in.ashokit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.ashokit.entity.citizenPlan;

public interface citizenPlanRepo extends JpaRepository<citizenPlan, Integer> {

	@Query("select distinct (planName) from citizenPlan ")
	List<String> getAllPlans();
	
	
//	@Query("select distinct (planName) from CITIZENPLAN ")
//	List<String> getAllPlans();

}
