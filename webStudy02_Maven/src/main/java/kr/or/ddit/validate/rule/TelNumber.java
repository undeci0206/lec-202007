package kr.or.ddit.validate.rule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
//3.실질적으로 코드를 돌려서 validation check해주는 것 찾기
@Constraint(validatedBy=TelNumberValidator.class) //속성명이 value가 아니기 때문에 생략 불가
public @interface TelNumber {
	String value() default "\\d{2,3}-\\d{3,4}-\\d{4}"; 
	String placeholder() default "000-0000-0000";
	
	String message() default "{kr.or.ddit.validate.rule.TelNumber.message}"; //{} message template의 형태
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default { };
}
