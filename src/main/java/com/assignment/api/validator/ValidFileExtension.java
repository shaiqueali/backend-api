package com.assignment.api.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.TYPE_PARAMETER;

@Documented
@Target({TYPE, ANNOTATION_TYPE, FIELD, PARAMETER, TYPE_PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ValidFileExtensionValidator.class})
public @interface ValidFileExtension {

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String message() default "{com.assignment.api.validator.annotation.ValidFileExtension.message}";

}
