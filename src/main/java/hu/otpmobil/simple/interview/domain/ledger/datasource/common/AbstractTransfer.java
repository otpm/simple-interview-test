package hu.otpmobil.simple.interview.domain.ledger.datasource.common;

import lombok.Data;

import javax.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public abstract class AbstractTransfer {
	private PartnerAddress partnerAddress;
	@NotNull
	private Long id;
	@NotNull
	private TransferType type;
	@NotNull
	private Double amount;
	@NotNull
	private LocalDateTime transferDate;
	@NotNull
	private LocalDateTime bankBookingDate;

	public LocalDate getDate() {
		return transferDate != null ? transferDate.toLocalDate() : null;
	}
}
