package hu.otpmobil.simple.interview.domain.ledger;

import hu.otpmobil.simple.interview.domain.ledger.common.LedgerRow;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.Assert.isTrue;

@Getter
public class RuleRunResult {

	public static final RuleRunResult EMPTY = new RuleRunResult();

	private List<LedgerRow> ledgerRows = new ArrayList<>();

	public void mergeInto(RuleRunResult other) {
		isTrue(other != null, "Other result is null");
		ledgerRows.addAll(other.getLedgerRows());
	}

}
