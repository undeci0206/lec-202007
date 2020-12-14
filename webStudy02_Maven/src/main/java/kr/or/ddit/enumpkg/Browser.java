package kr.or.ddit.enumpkg;

//<enum 특성>
//1. 자기 타입의 객체를 미리 만들어놓는 구조
//2. 자기 내부에서 미리 선언된 만큼만 인스턴스의 객체 생성 가능
public enum Browser{ //클래스 
	EDG("엣지"), CHROME("크롬"), TRIDENT("익스플로러"), OTHER("기타"); // 이들의 type: Browser 

	private String browserName;
	//생성자 만들기
	Browser(String browserName){
		this.browserName = browserName; //외부에서 이름 넣어주기 가능!
	}
	
	public String getBrowser(){
		return browserName;
	}
	
	public static String getBrowserName(String agent){
		Browser[] browsers = values(); //values():해당 열거체의 모든 상수를 저장한 배열을 생성하여 반환 (브라우저의 배열 타입)
		Browser finded = OTHER;
		for(Browser temp : browsers){
			//temp.name() //자동으로 할당된 name property 가져올 수 있음.
			if(agent.toUpperCase().contains(temp.name())){ //Map - dynamic. enum - 상수로 만들어 준 것
				finded = temp; //찾았다는 정보는 finded가 가지고 있다. 찾았다면 더이상 진행할 필요 없음
				break;
			}
		}
		return finded.getBrowser();
	}
	
}
