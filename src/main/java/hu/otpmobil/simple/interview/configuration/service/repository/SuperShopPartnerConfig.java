package hu.otpmobil.simple.interview.configuration.service.repository;

import hu.otpmobil.simple.interview.configuration.service.PartnerInfo;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SuperShopPartnerConfig implements PartnerInfo {

	@NotNull
	private String taxNumber;

	@NotNull
	private String name;

	@NotNull
	private String city;

	@NotNull
	private String zip;

	@NotNull
	private String address;

	@NotNull
	private String countryCode;

}
