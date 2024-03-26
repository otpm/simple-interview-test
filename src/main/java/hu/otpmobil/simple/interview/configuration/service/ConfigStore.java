package hu.otpmobil.simple.interview.configuration.service;

import java.util.List;
import java.util.Map;

public interface ConfigStore {

	Map<String, Map<String, String>> findConfigValuesByKeys(List<String> keys);

}
