package br.com.alura.school.support.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Payload;


@Target({ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface UserIValid {
	String message() default "Validation error";
	
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
