package com.gizmo;

import com.gizmo.utils.CharsetEnum;
import com.gizmo.utils.GizmoEncrypt;
import com.gizmo.utils.XmlUtil;
import com.gizmo.utils.XmlWrapper;
import com.gizmo.utils.vo.Student;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMain {
    public static void main(String[] args) {
        encrypt();
		System.out.println("");
		xml();
    }

	public static void xml() {
		System.out.println("================ xml test ================");

		try {

			System.out.println("<Map to XML>");
			Map<String, Object> map = new HashMap();
			map.put("name", "Zo");
			map.put("age", "20");
			map.put("gender", "male");
			System.out.println(XmlUtil.doMapToXML(map));

			System.out.println("<Map to XML with except Column>");
			ArrayList<String> exceptCol = new ArrayList<>();
			exceptCol.add("name");
			exceptCol.add("gender");
			System.out.println(XmlUtil.doMapToXML(map, exceptCol));

			System.out.println("<Vo(Dto) to XML>");
			Student student = new Student();
			student.setAddress("서울 하늘아래 어딘가...");
			student.setAge(100);
			student.setPhoneNo("000-0000-0000");
			student.setGender("male");
			student.setName("라면사리");
			System.out.println(XmlUtil.doObjectToXML(student));


			System.out.println("<Vo(Dto) List to XML>");
			List<Student> list = new ArrayList();
			for (int i = 0; i < 5; i++){
				student = new Student();
				student.setAddress("서울 하늘아래 어딘가...");
				student.setAge(100+i);
				student.setPhoneNo("000-0000-0000");
				student.setGender("male");
				student.setName("라면사리");

				list.add(student);
			}

			exceptCol = new ArrayList<>();
			exceptCol.add("name");
			exceptCol.add("gender");

			System.out.println(XmlUtil.doListToXML(list, exceptCol));
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}

		System.out.println("================ xml test end ================");
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
