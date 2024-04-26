package in.ashokit.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.ashokit.entity.citizenPlan;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class PdfGenerator {

	public void generatePdf(List<citizenPlan> data, HttpServletResponse response,File f) {
		try (Document document = new Document(PageSize.A4)) {
			PdfWriter.getInstance(document, response.getOutputStream());
			PdfWriter.getInstance(document, new FileOutputStream(f));
			document.open();
			Paragraph p = new Paragraph("Plan test pdf");
			p.setAlignment("center");
			document.add(p);

			PdfPTable table = new PdfPTable(6);
			table.addCell("Citizen name");
			table.addCell("plan name");
			table.addCell("plan status ");
			table.addCell("plan start date ");
			table.addCell("plan end date ");
			table.addCell("Benefit amount");
//			List<citizenPlan> data = repo.findAll();

			for (citizenPlan plan : data) {
				table.addCell(plan.getCitizenName());
				table.addCell(plan.getPlanName());
				table.addCell(plan.getPlanStatus());
				table.addCell(plan.getPlanStartDate().toString());
				table.addCell(plan.getPlanEndDate().toString());
				table.addCell(plan.getBenefitAmt() == null ? "N.A." : "200");
			}

			document.add(table);
			
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		}

	}

}
