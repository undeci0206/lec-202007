package kr.or.ddit.member;

import kr.or.ddit.vo.MemberVO;

//unchecked
public class UserNotFoundException extends RuntimeException{
	//ctrl shift s -> Generate constructors from superclass...
	public UserNotFoundException() {
		super();
	}
	
	public UserNotFoundException(MemberVO member) {
		super(member.getMem_id()+"에 해당하는 유저가 없음");
	}

	public UserNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UserNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserNotFoundException(String message) {
		super(message);
	}

	public UserNotFoundException(Throwable cause) {
		super(cause);
	}
	
	
}
