package hu.otpmobil.simple.interview.domain.ledger.advancement;

import hu.otpmobil.simple.interview.common.Consts;
import hu.otpmobil.simple.interview.domain.ledger.RuleRunResult;
import hu.otpmobil.simple.interview.domain.ledger.common.AbstractRuleService;
import hu.otpmobil.simple.interview.domain.ledger.common.LedgerKey;
import hu.otpmobil.simple.interview.domain.ledger.common.LedgerRow;
import hu.otpmobil.simple.interview.domain.ledger.common.RowType;
import hu.otpmobil.simple.interview.domain.ledger.datasource.advancement.AdvancementPaidTransfer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class AdvancementPaidRuleServiceImpl extends AbstractRuleService implements AdvancementPaidRuleService {

	private static final String REFERENCE_PREFIX_ADV = "ADV";
	private static final String NOTE_PREFIX_PAID = "Szállítói előleg | visz. | ";

	@Override
	public RuleRunResult execute(AdvancementPaidTransfer advancement) {
		if (advancement.getAmount() == 0.0) {
			return RuleRunResult.EMPTY;
		}

		RuleRunResult response = new RuleRunResult();
		LedgerRow row1 = createPaidAdvancementRow(advancement);
		row1.setLedgerKey(advancement.getAmount() < 0 ? LedgerKey.T : LedgerKey.K);
		row1.setConsolidationCode(Consts.CONS_100090);
		row1.setSpecialCode(Consts.SPECIAL_CODE_G);
		row1.setPaymentCondition("");
		row1.setType(RowType.K);
		response.getLedgerRows().add(row1);

		LedgerRow row2 = createPaidAdvancementRow(advancement);
		row2.setLedgerKey(advancement.getAmount() < 0 ? LedgerKey.K : LedgerKey.T);
		row2.setConsolidationCode(Consts.CONS_100000);
		row2.setLedgerNumber(Consts.LNUM_317205000);
		response.getLedgerRows().add(row2);

		return response;
	}

	private LedgerRow createPaidAdvancementRow(AdvancementPaidTransfer advancement) {
		LedgerRow row = createDefaultRow();
		row.setAmount(advancement.getAmount());
		String reference = REFERENCE_PREFIX_ADV + advancement.getId();
		row.setReference(reference);
		row.setAssignment(reference);
		row.setNote(NOTE_PREFIX_PAID + advancement.getPartnerAddress().getName());
		row.updateWithAddress(advancement.getPartnerAddress());
		return row;
	}

}
