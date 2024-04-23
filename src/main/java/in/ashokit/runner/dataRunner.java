package in.ashokit.runner;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import in.ashokit.entity.citizenPlan;
import in.ashokit.repo.citizenPlanRepo;

@Component
public class dataRunner implements ApplicationRunner {
	@Autowired
	private citizenPlanRepo repo;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		repo.deleteAll();
		citizenPlan c1 = new citizenPlan();
		c1.setCitizenName("john");
		c1.setGender("Male");
		c1.setPlanName("cash");
		c1.setPlanStatus("approved");
		c1.setPlanStartDate(LocalDate.now());
		c1.setPlanEndDate(LocalDate.now().plusMonths(6));

		citizenPlan c2 = new citizenPlan();
		c2.setCitizenName("cjohn");
		c2.setGender("fe-Male");
		c2.setPlanName("food");
		c2.setPlanStatus("approved");
		c2.setPlanStartDate(LocalDate.now());
		c2.setPlanEndDate(LocalDate.now().plusMonths(6));
		c2.setBenefitAmt(1200.2);
		repo.saveAll(Arrays.asList(c1, c2));

	}

}
