package com.gizmo.utils;

import com.gizmo.utils.kisa.SeedUtil;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 암호화 관련
 */
public class GizmoEncrypt {

    //암호화키.
    private static final String encryptKey = "q3sw5d7r7$+&*)%0";

    /**
     * @deprecated seedKey를 파라미터로 받는 getEncryptString를 사용하길 권장함.
     * 주어진 문자열을 seed128로 암호화 하여 돌려준다.
     * @param str 암호화할 문자열.
     * @return 암호화된 문자열.
     * @throws Exception
     */
    @Deprecated
    public static String getEncryptString(String str) throws Exception{

        int[] key = SeedUtil.getSeedRoundKey(encryptKey);
//      return Base64.encode(seed.encrypt(str, Util.encryptKey.getBytes(), "UTF-8"));
        return SeedUtil.getSeedEncrypt(str, key);
    }

    /**
     * 특정 암호화키로 주어진 문자열을 seed128로 암호화 하여 돌려준다.
     * @param str 암호화할 문자열.
     * @param seedKey 암호화키
     * @return
     * @throws Exception
     */
    public static String getEncryptString(String str, String seedKey) throws Exception{

        int[] key = SeedUtil.getSeedRoundKey(seedKey);
//      return Base64.encode(seed.encrypt(str, Util.encryptKey.getBytes(), "UTF-8"));
        return SeedUtil.getSeedEncrypt(str, key);
    }

    /**
     * @deprecated seedKey를 파라미터로 받는 getDecryptString 사용하길 권장함.
     * 주어진 암호화문을 복호화하여 돌려준다.
     * @param str
     * @return 복호화된 문자열
     * @throws Exception
     */
    @Deprecated
    public static String getDecryptString(String str) throws Exception{
        int[] key = SeedUtil.getSeedRoundKey(encryptKey);
        return SeedUtil.getSeedDecrypt(str, key);
//      byte[] encryptbytes = Base64.decode(nullToEmpty(str));
//      String decryptText = seed.decryptAsString(encryptbytes, Util.encryptKey.getBytes(), "UTF-8");
//      return decryptText;
    }

    /**
     * 특정 암호화키로 주어진 암호화문을 복호화하여 돌려준다.
     * @param str
     * @param seedKey
     * @return
     * @throws Exception
     */
    public static String getDecryptString(String str, String seedKey) throws Exception{
        int[] key = SeedUtil.getSeedRoundKey(seedKey);
        return SeedUtil.getSeedDecrypt(str, key);
//      byte[] encryptbytes = Base64.decode(nullToEmpty(str));
//      String decryptText = seed.decryptAsString(encryptbytes, Util.encryptKey.getBytes(), "UTF-8");
//      return decryptText;
    }

	/**
     * 문자 또는 문자열을 SHA-512로 암호화 한다.
     * @param text 암호화 대상 문자열.
     * @return
     */
    public static String getSha512Hex(String text) {

        MessageDigest md;
        String message = text;
        String mes1 = "";
        String mes2 = "";
        try {
            md = MessageDigest.getInstance("SHA-512");

            md.update(message.getBytes());
            byte[] mb = md.digest();
            for (int i = 0; i < mb.length; i++) {
                byte temp = mb[i];
                String s = Integer.toHexString(new Byte(temp));
                while (s.length() < 2) {
                    s = "0" + s;
                }
                s = s.substring(s.length() - 2);
                mes1 += s;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return mes1;
    }

	/**
     * 문자 또는 문자열을 SHA-512로 암호화 한다.
     * @param text 암호화 대상 문자열.
     * @param charEnum 암호화 캐릭터셋. 캐릭터셋에 따라 암호화 값이 달라진다.
     * @return
     */
    public static String getSha512Hex(String text, CharsetEnum charEnum) {

        MessageDigest md;
        String message = text;
        String mes1 = "";
        String mes2 = "";
        try {
            md = MessageDigest.getInstance("SHA-512");

            md.update(message.getBytes(charEnum.getCharset()));
            byte[] mb = md.digest();
            for (int i = 0; i < mb.length; i++) {
                byte temp = mb[i];
                String s = Integer.toHexString(new Byte(temp));
                while (s.length() < 2) {
                    s = "0" + s;
                }
                s = s.substring(s.length() - 2);
                mes1 += s;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return mes1;
    }

	/**
     * @deprecated CharsetEnum를 파라미터로 받는 getSha512Hex 사용하길 권장함.
     * 문자 또는 문자열을 SHA-512로 암호화 한다.
     *
     * @param text 암호화 대상 문자열.
     * @param charset 암호화 캐릭터셋. 캐릭터셋에 따라 암호화 값이 달라진다.
     * @return
     */
    @Deprecated
    public static String getSha512Hex(String text, String charset) {

        MessageDigest md;
        String message = text;
        String mes1 = "";
        String mes2 = "";
        try {
            md = MessageDigest.getInstance("SHA-512");

            md.update(message.getBytes(charset));
            byte[] mb = md.digest();
            for (int i = 0; i < mb.length; i++) {
                byte temp = mb[i];
                String s = Integer.toHexString(new Byte(temp));
                while (s.length() < 2) {
                    s = "0" + s;
                }
                s = s.substring(s.length() - 2);
                mes1 += s;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return mes1;
    }

    /**
     * 문자열을 base64로 인코딩 한다.
     * @param str
     * @return
     */
    public static String getBase64Encode(String str) {
    	return Base64.encodeBase64String(str.getBytes());
    }

    /**
     * 문자열을 charEnum에 해당하는 캐릭터셋의 base64로 인코딩 한다.
     * @param str base64 인코딩할 문자열.
     * @param charEnum 문자열의 캐릿터셋.
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getBase64Encode(String str, CharsetEnum charEnum) throws UnsupportedEncodingException {

    	return Base64.encodeBase64String(str.getBytes(charEnum.getCharset()));
    }

    /**
     * base64인코딩된 문자열을 다시 디코딩 한다.
     * @param base64Str
     * @return
     */
    public static String getBase64Decode(String base64Str) {
    	return new String(Base64.decodeBase64(base64Str));
    }

    /**
     * base64인코딩된 문자열을 charEnum에 해당하는 캐릭터셋으로 다시 디코딩 한다.
     * @param base64Str
     * @param charEnum
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getBase64Decode(String base64Str, CharsetEnum charEnum) throws UnsupportedEncodingException {

    	return new String(Base64.decodeBase64(base64Str.getBytes(charEnum.getCharset())));
    }
}
