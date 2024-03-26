package hu.otpmobil.simple.interview.domain.ledger.advancement;

import hu.otpmobil.simple.interview.domain.ledger.RuleRunResult;
import hu.otpmobil.simple.interview.domain.ledger.datasource.advancement.AdvancementPaidTransfer;

import javax.validation.Valid;

public interface AdvancementPaidRuleService {
	RuleRunResult execute(@Valid AdvancementPaidTransfer advancement);
}
