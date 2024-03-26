package hu.otpmobil.simple.interview.domain.ledger.datasource.common;

import hu.otpmobil.simple.interview.configuration.service.PartnerInfo;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString
public class PartnerAddress implements PartnerInfo {

	@NotNull
	private Long id;
	private String name;
	private String partnerCode;
	private String bankAccountNumber;
	private String taxNumber;
	private String city;
	private String zip;
	private String address;
	private String countryCode;

}
