package kr.or.ddit.explorer;

import java.io.File;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of="key")
public class FileWrapper implements Comparable<FileWrapper>{
	//	private File adaptee;  //내 자신이 파일이 되기 때문에 주석 처리
	
	public FileWrapper(File adaptee, String relativePath) {
		//adaptee가 왔을 때 이들을 결정해줌
		this.title = adaptee.getName();
		this.key = this.title; //ID(relativePath) 에 해당
		this.expanded = adaptee.isDirectory()?false:true; //펼침 여부
		this.extraClasses = adaptee.isDirectory()?"folder":"file";
		this.folder = adaptee.isDirectory();
		//statusNodeType : null값. 건너뛰기
		this.lazy = this.folder; 
		//selected : 뭔가를 선택했을 때인데, 선택할 필요 없으므로 넘어감
		this.tooltip = this.title;
		
	}

	private String title;
	private String key;
	private boolean expanded;
	private String extraClasses;
	private boolean folder;
	private String statusNodeType;//status code의 역할
	private boolean lazy;
	private boolean selected;
	private String tooltip;
	@Override
	public int compareTo(FileWrapper o) {
		int ret = 0;
		if(this.folder && !o.isFolder()) { //나는 폴더인데 상대방은 파일
			ret = -1;
		}else if(!this.folder && o.isFolder()) { //나는 파일인데 상대방은 폴더
			ret = 1;
		}else { //둘다 폴더이거나 둘다 파일이거나 -> 알파벳으로 결정
			//ret = this.title.compareTo(o.getTitle());
			//대소문자 구분않고 알파벳 순대로 정렬
			ret = this.title.toLowerCase().compareTo(o.getTitle());
			
		}
		return ret;
	}
	
}
