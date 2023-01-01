package com.automation.annotations;

import com.automation.enums.Authors;
import com.automation.enums.CategoryType;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(METHOD)
@Documented
public @interface FrameworkAnnotation {

  Authors[] author();

  CategoryType[] category();
}
