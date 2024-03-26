package hu.otpmobil.simple.interview.adapter.datasource.advancement;

import hu.otpmobil.simple.interview.domain.ledger.datasource.advancement.AdvancementPaidTransfer;

import java.time.LocalDateTime;
import java.util.List;

public interface AdvancementPaidSourceStore {
	List<AdvancementPaidTransfer> findByDate(LocalDateTime date);
}
