package in.ashokit.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.ashokit.entity.citizenPlan;
import in.ashokit.repo.citizenPlanRepo;
import in.ashokit.request.searchRequest;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

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
		citizenPlan plan = new citizenPlan();
		BeanUtils.copyProperties(SearchRequest, plan);
		return repo.findAll(Example.of(plan));
	}

	@Override
	public boolean exportExcel(HttpServletResponse response) {

		try (Workbook workbook = new HSSFWorkbook()) {
			Sheet sheet = workbook.createSheet("citizen-data");
			Row headerRow = sheet.createRow(0);
			headerRow.createCell(0).setCellValue("ID");
			headerRow.createCell(1).setCellValue("Citizen name");
			headerRow.createCell(2).setCellValue("plan name");
			headerRow.createCell(3).setCellValue("plan status ");
			headerRow.createCell(4).setCellValue("plan start date ");
			headerRow.createCell(5).setCellValue("plan end date ");
			headerRow.createCell(6).setCellValue("Benefit amount");
			List<citizenPlan> data = repo.findAll();
			int dataRow = 1;
			for (citizenPlan plan : data) {
				Row datarow = sheet.createRow(dataRow++);
				datarow.createCell(0).setCellValue(plan.getCitizenId());
				datarow.createCell(1).setCellValue(plan.getCitizenName());
				datarow.createCell(2).setCellValue(plan.getPlanName());
				datarow.createCell(3).setCellValue(plan.getPlanStatus());
				datarow.createCell(4).setCellValue(plan.getPlanStartDate().toString());
				datarow.createCell(5).setCellValue(plan.getPlanEndDate().toString());
				datarow.createCell(6).setCellValue(plan.getBenefitAmt() == null ? "N.A." : "200");

			}
			FileOutputStream fos = new FileOutputStream(new File("plan.xls"));
			workbook.write(fos);
			ServletOutputStream st = response.getOutputStream();
			workbook.write(st);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean exportPdf() {
		// TODO Auto-generated method stub
		return false;
	}

}
