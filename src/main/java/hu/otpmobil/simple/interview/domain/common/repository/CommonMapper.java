package hu.otpmobil.simple.interview.domain.common.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommonMapper {

	long nextId();

}