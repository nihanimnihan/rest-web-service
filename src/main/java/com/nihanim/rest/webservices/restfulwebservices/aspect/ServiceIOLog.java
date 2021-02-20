package com.nihanim.rest.webservices.restfulwebservices.aspect;

import java.lang.annotation.*;

@Target(value = {ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ServiceIOLog {

}
