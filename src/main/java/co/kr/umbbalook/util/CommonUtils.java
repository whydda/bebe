package co.kr.umbbalook.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by whydda on 2017-07-24.
 */
public class CommonUtils {

    /***
     * 검색결과가 페이징이 필요하면 무조건 사용
     * @param paramMap
     * @return
     */
    public static void setPageInfo(Map<String, Object> paramMap){
        int startNum = String.valueOf(paramMap.get("selectNum")).equals("null") ? 1 : Integer.parseInt(String.valueOf(paramMap.get("selectNum"))); //선택된 값 없으면 무조건 1이다.
        int pageListSize = String.valueOf(paramMap.get("pageListSize")).equals("null") ? 10 : Integer.parseInt(String.valueOf(paramMap.get("pageListSize"))); //페이지에 리스트 개수를 몇개씩 보여줄 것인가? 10 30 50 100 등등
        paramMap.put("selectNum", startNum);
        paramMap.put("pageListSize", pageListSize);
        paramMap.put("startNum", startNum == 1 ? 1 : (startNum - 1) *  pageListSize + 1);
        paramMap.put("endNum", startNum * pageListSize);
    }

    /***
     * 검색결과가 페이징이 필요하면 무조건 사용
     * @param paramMap
     * @return
     */
    public static Map<String, Object> getPageInfo(Map<String, Object> paramMap, int totalCnt){
        Map<String, Object> pageInfo = new HashMap<>();

        int tempPageListSize = Integer.parseInt(String.valueOf(paramMap.get("pageListSize")));
        int tempSelectNum = Integer.parseInt(String.valueOf(paramMap.get("selectNum")));

        //페이징 번호
        pageInfo.put("selectNum", tempSelectNum); //선택된 페이지
        pageInfo.put("prevNum", tempSelectNum - 1 == 0 ? 1 : tempSelectNum - 1);
        pageInfo.put("nextNum", tempSelectNum == (totalCnt / tempPageListSize) ? tempSelectNum : tempSelectNum + 1);
        pageInfo.put("firstNum", 1);
        pageInfo.put("lastNum", (totalCnt / tempPageListSize));

        return pageInfo;
    }

    /**
     * 쿠키값 저장
     * @param res
     * @param strCookieName
     * @param strValue
     * @param nMaxAge
     * @param strComment
     */
    public static void setCookie(HttpServletResponse res
            , String strCookieName
            , String strValue
            , int nMaxAge
            , String strComment) {

        Cookie cookie = new Cookie(strCookieName, strValue);
        cookie.setVersion(0);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(nMaxAge);
        cookie.setComment(strComment);
        res.addCookie(cookie);
    }

    /**
     * 쿠키값 불러오기
     * @param req
     * @param strCookieName
     * @return
     */
    public static String getCookie(HttpServletRequest req
            , String strCookieName) {
        Cookie cookies[] = req.getCookies();
        Cookie cookie = null;
        if (cookies != null) {
            for (int i = 0; i < cookies.length; ++i) {
                if (cookies[i].getName().equals(strCookieName)) {
                    cookie = cookies[i];
                    break;
                }
            }
        }
        String strValue = "";
        try {
            strValue = cookie.getValue();
        } catch (Exception e) {}
        return strValue;
    }

    /**
     * 값이 없는 Object 객체 삭제
     * @param i
     * @return
     */
    public static boolean chkRemoveObj(Map<String, String> i) {
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        atomicBoolean.set(false);

        i.forEach((k, v) -> {
            if(StringUtils.isEmpty(v)){
                atomicBoolean.set(true);
            }
        });

        return atomicBoolean.get();
    }
}
