package hu.otpmobil.simple.interview.domain.ledger.datasource.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TransferType {
	ACQUIRE("ACQ"),
	ADVANCEMENT("ADV"),
	COMMISSION("COM"),
	DISTRIBUTION("DIST"),
	INVOICE("INV"),
	RECTIFY("REC"),
	REFUND("REF"),
	SETTLEMENT("SET"),
	UNKNOWN("UNK");

	@Getter
	private final String prefix;
}
