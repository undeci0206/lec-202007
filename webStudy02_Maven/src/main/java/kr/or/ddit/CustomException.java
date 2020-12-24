package kr.or.ddit;

//개발자가 일일이 예외처리 X
//알아서 호출자에게 호출 제어권 넘어가게
//단 한번도 예외를 처리하지 않았다면 virtual machine에게 넘어가게
//->RuntimeException!
public class CustomException extends RuntimeException{
	
	//alt shift s -> superclass로 생성
	
	public CustomException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public CustomException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CustomException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public CustomException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
