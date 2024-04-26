package in.ashokit.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.ashokit.entity.citizenPlan;
import in.ashokit.repo.citizenPlanRepo;
import in.ashokit.request.searchRequest;
import in.ashokit.utils.EmailSender;
import in.ashokit.utils.ExcelGenerator;
import in.ashokit.utils.PdfGenerator;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportSeviceImpl implements reportService {
	@Autowired
	private citizenPlanRepo repo;

	@Autowired
	private PdfGenerator PdfGenerator;

	@Autowired
	private ExcelGenerator excelgenerator;

	@Autowired
	public EmailSender mailSender;

	@Override
	public List<String> getPlan() {
		return repo.getAllPlans();
	}

	@Override
	public List<String> getPlanStatuses() {
		return null;
	}

	@Override
	public List<citizenPlan> search(searchRequest SearchRequest) {
		citizenPlan plan = new citizenPlan();
		BeanUtils.copyProperties(SearchRequest, plan);
		return repo.findAll(Example.of(plan));
	}

	@Override
	public boolean exportExcel(HttpServletResponse response) {
		File f = new File("plan.xls");
		excelgenerator.generateExcel(response, repo.findAll(), f);
		mailSender.sendEmail("to", "ui", "hiu", f);
	
		return true;
	}

	@Override
	public boolean exportPdf(HttpServletResponse response) {
		File f = new File("plan.pdf");
		PdfGenerator.generatePdf(repo.findAll(), response,f);
		mailSender.sendEmail("to", "ui", "hiu", f);
		return true;
	}

}
