package com.nihanim.rest.webservices.restfulwebservices.aspect;


import java.lang.annotation.*;

@Target(value = {ElementType.ANNOTATION_TYPE, ElementType.PACKAGE, ElementType.METHOD, ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface LogExecutionTime {

}
