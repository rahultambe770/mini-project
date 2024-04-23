package in.ashokit.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.ashokit.entity.citizenPlan;
import in.ashokit.repo.citizenPlanRepo;
import in.ashokit.request.searchRequest;

@Service
public class ReportSeviceImpl implements reportService {
	@Autowired
	private citizenPlanRepo repo;

	@Override
	public List<String> getPlan() {
		return repo.getAllPlans();
	}

	@Override
	public List<String> getPlanStatuses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<citizenPlan> search(searchRequest SearchRequest) {
//		

		citizenPlan plan = new citizenPlan();
		BeanUtils.copyProperties(SearchRequest, plan);
		return repo.findAll(Example.of(plan));
		
	}

	@Override
	public boolean exportExcel() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean exportPdf() {
		// TODO Auto-generated method stub
		return false;
	}

}
