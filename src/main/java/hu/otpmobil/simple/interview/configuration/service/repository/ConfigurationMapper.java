package hu.otpmobil.simple.interview.configuration.service.repository;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ConfigurationMapper {

	@MapKey("kkey")
	Map<String, Map<String, String>> selectConfigValuesByKeys(@Param("keys") List<String> keys);

}