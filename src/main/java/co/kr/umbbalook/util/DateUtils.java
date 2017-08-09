package co.kr.umbbalook.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by whydda on 2017-08-01.
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils{

    final static String DEFAULT_FORMAT = "yyyyMMdd";

    /**
     * 오늘 날짜를 String 형식으로 리턴
     * format 타입 변경을 통해서 원하는 날짜 형식을 리턴 받을 수 있다.
     * @param format
     * @return
     */
    public static String getToDay(String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format.equals("") ? DEFAULT_FORMAT : format);
        Calendar c1 = Calendar.getInstance();
        String strToday = sdf.format(c1.getTime());
        return strToday;
    }
}
