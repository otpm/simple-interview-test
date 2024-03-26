package hu.otpmobil.simple.interview.adapter.datasource.advancement.repository;

import hu.otpmobil.simple.interview.adapter.datasource.advancement.AdvancementPaidSourceStore;
import hu.otpmobil.simple.interview.domain.ledger.datasource.advancement.AdvancementPaidTransfer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdvancementPaidSourceStoreImpl implements AdvancementPaidSourceStore {

	private final AdvancementPaidSourceMapper mapper;

	@Override
	public List<AdvancementPaidTransfer> findByDate(LocalDateTime date) {
		return mapper.selectByDate(date);
	}

}
