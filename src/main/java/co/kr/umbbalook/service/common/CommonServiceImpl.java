package co.kr.umbbalook.service.common;

import co.kr.umbbalook.service.mapper.CommonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by whydda on 2017-08-03.
 */
@Service
public class CommonServiceImpl {
    @Autowired
    private CommonMapper commonMapper;

    @Transactional(readOnly = true)
    public Map<String, Object> selectTest() {
        return commonMapper.selectTest();
    }
}
