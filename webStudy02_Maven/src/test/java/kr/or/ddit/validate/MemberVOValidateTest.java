package kr.or.ddit.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.MemberVO;

public class MemberVOValidateTest {
	private Validator validator;
	
	@Before //각 test case 이전에 한 번씩 호출
	public void setup() {
		ValidatorFactory factory = Validation
				.byDefaultProvider()
				.configure()
				.messageInterpolator(
					new ResourceBundleMessageInterpolator(
						//basename : classpath없음. 확장자 없음. 로케일없음. /는 .으로 변경
						new PlatformResourceBundleLocator("kr.or.ddit.msg.message")
					)
				).buildValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Test
	public void validateTest() {
		//validation Target
		MemberVO member = new MemberVO();
		member.setMem_mail("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		//제약 조건 위반 //generic Type에 따라 return값 결정
		//validate() : 객체 하나와 가변 파라미터(없어도 되고 1개 이상) 요구
		Set<ConstraintViolation<MemberVO>> violationSet = validator.validate(member, DeleteGroup.class);//erros의 역할
		for(ConstraintViolation<MemberVO> violation : violationSet) {
			String propertyName = violation.getPropertyPath().toString();
			String message = violation.getMessage();
			System.out.printf("%s : %s \n", propertyName, message); //errors의 key/value
		}
	}
}
