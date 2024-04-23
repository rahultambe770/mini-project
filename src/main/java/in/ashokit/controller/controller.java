package in.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.entity.citizenPlan;
import in.ashokit.request.searchRequest;
import in.ashokit.service.reportService;

@RestController
public class controller {
	@Autowired
	private reportService service;

	@GetMapping("/plan")
	public List<String> getPlan() {
		return service.getPlan();
	}
	
	@PostMapping("/all")
	public List<citizenPlan> getAll(@RequestBody searchRequest data) {
//		System.out.println(data);
		return service.search(data);
	}

}
