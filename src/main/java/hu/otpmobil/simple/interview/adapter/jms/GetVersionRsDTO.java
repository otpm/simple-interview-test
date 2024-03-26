package hu.otpmobil.simple.interview.adapter.jms;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetVersionRsDTO {

	private String version;
	private Long time;

}
