package in.ashokit.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class searchRequest {

	private String planName;
	private String planStatus;
	private String gender;
	private LocalDate startDate;
	private LocalDate endDate;
}
