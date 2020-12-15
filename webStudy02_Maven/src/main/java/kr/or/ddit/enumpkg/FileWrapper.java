package kr.or.ddit.enumpkg;

import java.io.File;

public class FileWrapper extends File{
//	private File adaptee;  //내 자신이 파일이 되기 때문에 주석 처리

	public FileWrapper(File adaptee, String relativePath) {
		super(adaptee.getAbsolutePath()); //상위 객체 가짐, 파일이 가진 나머지 인터페이스도 채워짐
//		this.adaptee = adaptee; //주석 처리
		this.relativePath = relativePath;
		this.clzName = adaptee.isDirectory()?"folder":"file";
	}

	//adaptee가 가지고 있지 않은 추가적인 기능 제공하자
	private String clzName;
	private String relativePath; //contextPath 이후의 경로를 넣어보자.
	public String getRelativePath() {
		return relativePath;
	}
	public String getClzName() {
		return clzName;
	}
}
