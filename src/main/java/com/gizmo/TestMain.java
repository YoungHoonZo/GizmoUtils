package com.gizmo;

import com.gizmo.utils.CharsetEnum;
import com.gizmo.utils.GizmoEncrypt;

import java.io.UnsupportedEncodingException;

public class TestMain {
    public static void main(String[] args) {
        encrypt();
    }

    public static void encrypt() {

		System.out.println("================ encrypt test ================");
		try {
			String oriStr = "abcdefghijklmnopqrstuvwxyz";
			String key = "RDBN6au9si0Z+yKe+bK7AFAnPEdA+X/F";

            String encryptStr = GizmoEncrypt.getEncryptString(oriStr, key);

			System.out.println("Base64 Encoding : " + GizmoEncrypt.getBase64Encode(oriStr));
			System.out.println("Base64 Encoding UTF8 : " + GizmoEncrypt.getBase64Encode(oriStr, CharsetEnum.UTF8));
			System.out.println("Base64 Encoding UTF16 : " + GizmoEncrypt.getBase64Encode(oriStr, CharsetEnum.UTF16));
			System.out.println("Base64 Encoding EUCKR : " + GizmoEncrypt.getBase64Encode(oriStr, CharsetEnum.EUCKR));
			System.out.println("Base64 Encoding UTF16LE : " + GizmoEncrypt.getBase64Encode(oriStr, CharsetEnum.UTF16LE));
			System.out.println();

			System.out.println("Target text : " + oriStr);
			System.out.println("Encrypt Seed128 : " + encryptStr);
            System.out.println("Decrypt Seed128 : " + GizmoEncrypt.getDecryptString(encryptStr, key));

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("================ encrypt test end ================");
    }
}
