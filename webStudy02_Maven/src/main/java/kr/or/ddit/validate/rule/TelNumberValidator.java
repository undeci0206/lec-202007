package kr.or.ddit.validate.rule;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

//어떤 어노테이션과 한 쌍인지 알려줌 - TellNumber
public class TelNumberValidator implements ConstraintValidator<TelNumber, String>{
	private TelNumber telNumber;
	
	@Override
	public void initialize(TelNumber constraintAnnotation) {
		this.telNumber = constraintAnnotation;
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		boolean valid = true;
		if(value!=null && !value.isEmpty()){
			//전화번호 형식 체크
			valid = value.matches(telNumber.value());
		}
		return valid;
	}
}
