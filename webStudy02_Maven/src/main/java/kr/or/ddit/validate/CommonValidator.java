package kr.or.ddit.validate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;

public class CommonValidator<T> {
	//검증 수행 validator
	private Validator validator;
	{
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
	
	//가변파라미터는 가장 마지막 파라미터로 설정! 몇개가 들어올 지 모르기 때문
	public boolean validate(T validateTarget, Map<String, List<String>> errors, Class<?>... groups) {
		//call by reference
		Set<ConstraintViolation<T>> violationSet = validator.validate(validateTarget, groups);
		
		//validate() : constraint violations or an empty set if none 에러 없으면 빈 set돌아옴
		if(violationSet.size()>0) {
			//Map안의 entry로 바꿔서 넣어주기
			for(ConstraintViolation<T> violation :violationSet) {
				String propName = violation.getPropertyPath().toString();
				String message = violation.getMessage();
				//이전의 entry있는지 보고 있으면 기존거에 넣고 없으면 새로 만들어서 넣기
				List<String> msgList = errors.get(propName);
				if(msgList==null) { //첫번째 entry일 때
					msgList = new ArrayList<>();
					errors.put(propName, msgList);
				}
				//call by reference이기 때문에 먼저 만들고 add가능
				msgList.add(message);
			}
		}
		return violationSet.size()==0;
	}
}
