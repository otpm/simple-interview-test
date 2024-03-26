package hu.otpmobil.simple.interview.domain.ledger.datasource.advancement;

import java.time.LocalDateTime;
import java.util.List;

public interface AdvancementPaidSourceService {
	List<AdvancementPaidTransfer> findByDate(LocalDateTime date);
}
