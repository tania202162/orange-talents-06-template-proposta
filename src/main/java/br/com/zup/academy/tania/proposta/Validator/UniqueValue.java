package br.com.zup.academy.tania.proposta.Validator;

import java.lang.annotation.Retention;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = UniqueValueValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)

public @interface UniqueValue {
			
	String message() default "Não é permitido campo duplicado! Reveja CPF/CNPJ ou Email!";
	
	String fieldName();		
	
	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
		
	Class<?> domainClass();
}
