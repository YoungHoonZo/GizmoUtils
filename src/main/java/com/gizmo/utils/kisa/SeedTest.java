package com.gizmo.utils.kisa;

import org.apache.commons.codec.binary.Base64;

import java.util.ArrayList;

/**
 * 
 * 
 * @author yhzo
 * @version 1.0, 2008. 03. 11
 */
/*
 * History
 * -2008. 03. 11, Create, 손님138, [Description] 
 */

public class SeedTest {
	
	public static void main(String[] args) throws Exception {
		
		// 암호화키(암호화키 값이 변경될 경우 암호화 결과값이 다르기 때문에 변경하면 안됨.)
//		final String key = "235dFT%Y&53$%FYT";//VgManager
		final String key = "366#^^$@$ERFdgr5#";//Rose
//		final String key = "q3sw5d7r7$+&*)%0";//수능모의고사
		
		StringBuilder trace = new StringBuilder();
		
		int seedKey[] = SeedUtil.getSeedRoundKey(key);
		// e:암호화, d:복호화
		String div = "d";
		ArrayList al = new ArrayList();
		

		String[] array = new String[]{"dffvwon1fLkcN1S2pLdC+g==","mSw3x3bKCwH4rO3w1dTy4Q=="};
		
		
		SeedCipher seed = new SeedCipher();
		
		if(div.equals("e")){
			
			String tempEn = "";
			for(int i = 0; i < array.length; i++){
//				tempEn = Base64.encode(seed.encrypt(array[i], key.getBytes(), "UTF-8"));
				tempEn = SeedUtil.getSeedEncrypt(array[i], seedKey);
				System.out.println("encode : "+array[i] + " :" + tempEn);

			}
			
//			tempEn = Base64.encode(seed.encrypt("경기도 성남시", key.getBytes(), "UTF-8"));
//			System.out.println("encode : " + tempEn);
			
//			System.out.println("rtn : "+seed.readFile("c:\\lib\\key2.dat"));
			
		}
		else{
			for(int i = 0; i < array.length; i++){
//				byte[] encryptbytes = Base64.decode(array[i]);
				byte[] encryptbytes = Base64.decodeBase64(array[i]);
//				String decryptText = seed.decryptAsString(encryptbytes, key.getBytes(), "UTF-8");
				String decryptText = SeedUtil.getSeedDecrypt(array[i], seedKey);
				
//				System.out.println("key : " + key.getBytes());
//				System.out.println("decode : "+array[i] + " :" + decryptText);
				if(decryptText.indexOf(" ") == -1){
					System.out.println("decode : "+array[i] + " :" + decryptText);
					System.out.println("공백 X");
				}
				else{
					System.out.println("decode : "+array[i] + " :" + decryptText);
					System.out.println("공백 O");
				}
			}
//			int eI=0; 
//			int eJ=0;
//			try{
//				for(int i = 0; i < al.size(); i++){
//					eI = i;
//					String[] tmpArr = (String[]) al.get(i);
//					for(int j = 0; j < tmpArr.length; j++){
//						eJ = j;
//						byte[] encryptbytes = Base64.decode(tmpArr[j]);
//						String decryptText = seed.decryptAsString(encryptbytes, key.getBytes(), "UTF-8");
//						System.out.print(decryptText.replaceAll(" ", "") + ",");
//					}
//					System.out.println();
//				}
//			}catch(Exception e){
//				String[] eArray = (String[]) al.get(eI);
//				System.out.println();
//				System.out.println();
//				System.out.println("Error : "+eArray[eJ]);
//			}
			
		}
	}
}
