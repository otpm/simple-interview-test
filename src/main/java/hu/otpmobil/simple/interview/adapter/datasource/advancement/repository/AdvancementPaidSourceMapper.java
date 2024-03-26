package hu.otpmobil.simple.interview.adapter.datasource.advancement.repository;

import hu.otpmobil.simple.interview.domain.ledger.datasource.advancement.AdvancementPaidTransfer;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface AdvancementPaidSourceMapper {
	List<AdvancementPaidTransfer> selectByDate(LocalDateTime date);
}
