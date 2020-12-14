package kr.or.ddit.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.management.RuntimeErrorException;

public class SecurityUtils {
	public static String encyptSha512(String plain){
		//1. 단방향(해시 함수) : 다양한 입력 데이터를 일정 길이의 해시코드로 생성할 때 사용
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] encypted = md.digest(plain.getBytes()); //byte로 나눠서 넘김
			System.out.println(encypted.length);
			String encoded = Base64.getEncoder().encodeToString(encypted);
			System.out.println(encoded);
			return encoded;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e); //문제 생기면 톰캣이 인지하게
		}
	}
}
