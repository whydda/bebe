package co.kr.umbbalook.core.web;

/**
 * Created by whydda on 2017-07-13.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by whydda on 2017-03-03.
 */
public class DefaultParamsArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    ObjectMapper objectMapper;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return DefaultParams.class.isAssignableFrom(methodParameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {

        if(methodParameter.getParameterType().equals(DefaultParams.class)) {
            DefaultParams defaultParams = new DefaultParams();
            defaultParams.setMap(new LinkedHashMap());
            Iterator iterator = nativeWebRequest.getParameterNames();

//            while(iterator.hasNext()) {
//                objectMapper = new ObjectMapper();
//                String key = (String)iterator.next();
//                String value = nativeWebRequest.getParameter(key);
//                Object json = new JSONTokener(value).nextValue();
//                if(json instanceof JSONObject){
//                    objectMapper.readValue(value, Map.class).forEach((k, v) ->
//                            defaultParams.put((String) k, v))
//                    ;
//                }else if (json instanceof JSONArray){
//                    defaultParams.put(key, objectMapper.readValue(value, List.class));
//                } else {
//                    defaultParams.put(key, value);
//                }
//            }

            if(nativeWebRequest.getNativeRequest() instanceof MultipartHttpServletRequest){
                List<Map<String, Object>> uploadFileMap = new ArrayList<>();
                MultipartHttpServletRequest mpReq = (MultipartHttpServletRequest)nativeWebRequest.getNativeRequest();
                for(String name : mpReq.getFileMap().keySet()){
                    Map<String, Object> map = new HashMap<>();
                    map.put("key", name);
                    map.put("files",mpReq.getFiles(name));
                    uploadFileMap.add(map);
                }
                defaultParams.put("uploadFileMap", uploadFileMap);
            }

            //세션정보가 있는지 확인한다. 세션정보가 있을 경우 ROLE 이라는 param을 만든다.
            ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = sra.getRequest().getSession();

            if(StringUtils.equals(String.valueOf(session.getAttribute("ROLE")), "ADMIN")){
                defaultParams.getMap().put("ROLE", session.getAttribute("ROLE"));
            }else if(StringUtils.equals(String.valueOf(session.getAttribute("ROLE")), "USER")){
                defaultParams.getMap().put("ROLE", session.getAttribute("ROLE"));
            }else{
                defaultParams.getMap().put("ROLE", null);
            }

            //추가 파라미터 정의
            //defaultParams.put("filePath", "/attachFile" + File.separator + DateUtils.getToDay(""));

            return defaultParams;
        }
        return null;
    }
}
