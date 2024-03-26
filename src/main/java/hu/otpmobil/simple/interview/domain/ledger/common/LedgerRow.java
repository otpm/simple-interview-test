package hu.otpmobil.simple.interview.domain.ledger.common;

import hu.otpmobil.simple.interview.common.TimeUtil;
import hu.otpmobil.simple.interview.configuration.service.PartnerInfo;
import hu.otpmobil.simple.interview.domain.ledger.datasource.common.PartnerAddress;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Data
public class LedgerRow {
	private RowType type;
	private String taxNumber;
	private String name;
	private String city;
	private String zip;
	private String address;
	private String countryCode;
	private String bankKey;
	private String bankAccountNumber;
	private String bankCountry;
	private LocalDateTime ledgerDate;
	private LocalDateTime eventDate;
	private String reference;
	private String receiptHeader;
	private String currency;
	private LocalDateTime exchangeDate;
	private String exchangeType;
	private Double exchangeRate;
	private LedgerKey ledgerKey;
	private String ledgerNumber;
	private Double amount;
	private String vatCode;
	private Double vatAmount;
	private LocalDateTime dueDate;
	private String paymentCondition;
	private String paymentType;
	private String consolidationCode;
	private String costCenter;
	private String coOrder;
	private String assignment;
	private String note;
	private String ledgerAccountCode;
	private String product;
	private String client;
	private String analyticsId;
	private String correctedAccountNumber;
	private Double totalHuf;
	private String specialCode;

	public void updateWithDates(LocalDateTime date) {
		updateWithDates(date, date);
	}

	public void updateWithDates(LocalDateTime ledgerDate, LocalDateTime eventDate) {
		LocalDateTime monthStart = TimeUtil.startOfThisMonth();
		if (ChronoUnit.MONTHS.between(ledgerDate, monthStart) > 0) {
			ledgerDate = monthStart.minusMonths(1);
		}
		this.ledgerDate = ledgerDate;
		this.eventDate = eventDate;
	}

	public void processAbsolutValuesForAmounts() {
		amount = Math.abs(amount);
		if (vatAmount != null) {
			vatAmount = Math.abs(vatAmount);
		}
		if (totalHuf != null) {
			totalHuf = Math.abs(totalHuf);
		}
	}

	public void updateWithAddress(PartnerAddress partner) {
		address = partner.getAddress();
		city = partner.getCity();
		countryCode = partner.getCountryCode();
		name = partner.getName();
		taxNumber = partner.getTaxNumber();
		zip = partner.getZip();

		String accountNumber = partner.getBankAccountNumber();
		if (accountNumber == null || accountNumber.length() != 16 && accountNumber.length() != 17 && accountNumber.length() != 24) {
			return;
		}
		bankKey = accountNumber.substring(0, 8);
		String part1;
		if("-".equals(accountNumber.substring(8, 9))) {
			part1 = accountNumber.substring(9, 17);
		} else {
			part1 = accountNumber.substring(8, 16);
		}
		bankAccountNumber = accountNumber.length() == 24 ? part1 + "-" + accountNumber.substring(16, 24) : part1;
	}

	public void setPartnerInfo(PartnerInfo partnerInfo) {
		if(partnerInfo != null) {
			setTaxNumber(partnerInfo.getTaxNumber());
			setName(partnerInfo.getName());
			setCity(partnerInfo.getCity());
			setZip(partnerInfo.getZip());
			setAddress(partnerInfo.getAddress());
			setCountryCode(partnerInfo.getCountryCode());
		}
	}

	public void setDates(LocalDate ledgerDate, LocalDate receiptDate) {
		LocalDateTime actDate = TimeUtil.now();
		// TODO: Check why LocalDateTime and not LocalDate
		LocalDateTime ledgerDateTime = ledgerDate.atStartOfDay();
		setLedgerDate(ledgerDateTime);
		// TODO: receiptDate is this correct?
		setEventDate(receiptDate.atStartOfDay());
		if (ChronoUnit.MONTHS.between(actDate, ledgerDateTime) > 1L) {
			setLedgerDate(actDate.plusMonths(-1).withDayOfMonth(1));
		}
	}
}
