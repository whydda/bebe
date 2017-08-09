package co.kr.umbbalook.util;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by whydda on 2017-08-01.
 */
public class CollectionUtils {

    /**
     * main, detail list 합쳐주는 메서드
     * @param mainList
     * @param detailList
     * @param key
     * @return
     */
    public List<Map<String, Object>> listAddList(List<Map<String, Object>> mainList, List<Map<String, Object>> detailList, String key){
        for(Map<String, Object> mainMap : mainList){
            for(Map<String, Object> detailMap : detailList) {
                if(StringUtils.equals(mainMap.get(key).toString(), detailMap.get(key).toString())){
                    mainMap.put("detail", detailMap);
                    break;
                }
            }
        }

        return mainList;
    }
}
