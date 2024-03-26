package hu.otpmobil.simple.interview.domain.ledger.common;

import hu.otpmobil.simple.interview.common.Consts;

public abstract class AbstractRuleService {

	protected LedgerRow createDefaultRow() {
		LedgerRow row = new LedgerRow();
		row.setType(RowType.S);
		row.setVatAmount(0.0);
		row.setPaymentCondition(Consts.PCON_0001);
		row.setConsolidationCode(Consts.CONS_100090);
		row.setCurrency(Consts.CCY_HUF);
		row.setBankCountry(Consts.CNTRY_HU);
		return row;
	}

}
