package com.example.thepirates.api.dto.request;

import com.example.thepirates.api.dto.ProductSupplierInfo;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalTime;

public class BussinessHourValidator
        implements ConstraintValidator<BussinessHour, ProductSupplierInfo> {
    public void initialize(BussinessHour constraintAnnotation) {

    }

    @Override
    public boolean isValid(ProductSupplierInfo value, ConstraintValidatorContext context) {
        LocalTime open = value.getOpen();
        LocalTime close = value.getClose();
        return !open.isAfter(close) && !open.equals(close);
    }
}
