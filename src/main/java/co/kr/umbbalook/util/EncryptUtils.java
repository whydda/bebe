package co.kr.umbbalook.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by whydda on 2017-07-21.
 */
public class EncryptUtils {
    private final static Logger LOGGER = LoggerFactory.getLogger(EncryptUtils.class);
    /**
     * 7Bit 비밀번호 암호화
     */
    private String Ref = "`1234567890-=~!@#$%^&*()_+qwertyuiop[]QWERTYUIOP{}|asdfghjkl;ASDFGHJKL:zxcvbnm,./ZXCVBNM<>?";
    public String decode (String OrigString, String CipherVal) {
        String Temp="";
        if ( OrigString != null ) {
            for (int Count= 0; Count < OrigString.length(); Count++) {
                String TempChar = OrigString.substring (Count, Count+1);
                int Conv = cton(TempChar);

                int Cipher = Conv ^  Integer.parseInt(CipherVal);
                Temp += ntoc(Cipher);;
            }
        }

        return Temp;
    }

    private int cton (String Char) {
        return Ref.indexOf(Char);
    }

    private String ntoc (int Val) {
        return Ref.substring(Val, Val+1);
    }

    /**
     * SHA_256
     * @param b
     * @return
     */
    public static String hexForByte(byte b) {
        String Hex = Integer.toHexString((int) b & 0xff);

        boolean hasTwoDigits = (2 == Hex.length());

        if (hasTwoDigits)
            return Hex;
        else
            return "0" + Hex;
    }


    public static String hexForBytes(byte[] bytes) {
        StringBuilder sb = new StringBuilder();

        for (byte b : bytes)
            sb.append(hexForByte(b));
        return sb.toString();
    }


    /***
     * 암호화
     * @param plainText
     * @return
     */
    public static String enc_SHA256(String plainText){
        {

            //String plainText = "aaaa";
            String encryptedText = "";
            try{
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte encodeByte[] = digest.digest(plainText.getBytes());

                encryptedText = hexForBytes(encodeByte);

            }catch (NoSuchAlgorithmException e){
                LOGGER.error(e.getMessage(), e, e.getStackTrace());
            }
            return encryptedText;
        }
    }
}
