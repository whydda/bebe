package co.kr.umbbalook.service.mapper;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository("commonMapper")
public interface CommonMapper {
	Map<String, Object> selectTest();
}
