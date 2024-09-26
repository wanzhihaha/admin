package main.java.com.cellosquare.adminapp.common.util;

import java.security.SecureRandom;

public class RandomUtil {
	// 숫자만
	public static String get_random_numvalue(int limit) {
		SecureRandom rnd = new SecureRandom();
		StringBuffer buf =new StringBuffer();
		for(int i=0; i<limit; i++){
			buf.append(String.valueOf((int)(rnd.nextInt(10))));
		}
		return buf.toString();
	}
	
	// 영문만
	public static String get_random_alphlow_value(int limit) {
		SecureRandom rnd = new SecureRandom();
		StringBuffer buf =new StringBuffer();
		/*문자*/
		for(int i=0; i<limit; i++){
			buf.append((char)((int)(rnd.nextInt(26))+97));
		}
		return buf.toString().toLowerCase();
	}
	
	// 영문 + 숫자만
	public static String get_random_alphnumlow_value(int limit) {

		SecureRandom rnd = new SecureRandom();
		StringBuffer buf =new StringBuffer();

		for(int i=0; i<limit; i++){
		    // rnd.nextBoolean() 는 랜덤으로 true, false 를 리턴. true일 시 랜덤 한 소문자를, false 일 시 랜덤 한 숫자를 StringBuffer 에 append 한다.
		    if(rnd.nextBoolean()){
		        buf.append((char)((int)(rnd.nextInt(26))+97));
		    }else{
		        buf.append((rnd.nextInt(10)));
		    }
		}
		return buf.toString().toLowerCase();
	}
}
