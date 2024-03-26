package hu.otpmobil.simple.interview.configuration.service.repository;

import hu.otpmobil.simple.interview.configuration.service.ConfigStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ConfigStoreImpl implements ConfigStore {

	private final ConfigurationMapper commonMapper;

	@Override
	public Map<String, Map<String, String>> findConfigValuesByKeys(List<String> keys) {
		return commonMapper.selectConfigValuesByKeys(keys);
	}

}
