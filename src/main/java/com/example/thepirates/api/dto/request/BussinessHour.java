package com.example.thepirates.api.dto.request;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.CLASS;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = BussinessHourValidator.class)
@Target(TYPE)
@Retention(RUNTIME)
@Documented
public @interface BussinessHour {
    String message() default
            "영업 개시시간은 종료시간보다 같거나 이후 일 수 없습니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
