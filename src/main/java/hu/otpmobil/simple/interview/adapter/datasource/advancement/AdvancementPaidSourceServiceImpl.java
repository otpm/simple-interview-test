package hu.otpmobil.simple.interview.adapter.datasource.advancement;

import hu.otpmobil.simple.interview.domain.ledger.datasource.advancement.AdvancementPaidSourceService;
import hu.otpmobil.simple.interview.domain.ledger.datasource.advancement.AdvancementPaidTransfer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Validated
@Transactional
@RequiredArgsConstructor
public class AdvancementPaidSourceServiceImpl implements AdvancementPaidSourceService {

	private final AdvancementPaidSourceStore store;

	@Override
	public List<AdvancementPaidTransfer> findByDate(LocalDateTime date) {
		return store.findByDate(date);
	}

}
