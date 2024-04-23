package in.ashokit.service;

import java.util.List;

import in.ashokit.entity.citizenPlan;
import in.ashokit.request.searchRequest;

public interface reportService {
	public List<String> getPlan();

	public List<String> getPlanStatuses();

	public List<citizenPlan> search(searchRequest SearchRequest);

	public boolean exportExcel();

	public boolean exportPdf();
}
