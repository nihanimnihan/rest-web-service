package com.nihanim.rest.webservices.restfulwebservices.aspect;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Aspect
@Slf4j
public class LogAspect {

    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;

        log.info(joinPoint.getSignature() + " executed in " + executionTime + "ms");
        return proceed;
    }

    @Around("@annotation(ServiceIOLog)")
    public Object logServiceIOLog(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            String className = joinPoint.getSignature().getDeclaringTypeName();
            String methodName = joinPoint.getSignature().getName();
            Object result = joinPoint.proceed();
            ObjectMapper mapper = getJSONMapper();
            String response = result != null ? mapper.writeValueAsString(result): "null";
            String request = Arrays.stream(joinPoint.getArgs()).map(arg -> {
                try {
                    return mapper.writeValueAsString(arg);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    return "exception in ServiceIOLog objectMapper";
                }
            }).collect(Collectors.joining(", "));
            log.info("Method " + className + "." + methodName + " Request: " + request + " Response: " + response);
            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument " + Arrays.toString(joinPoint.getArgs()) + " in "
                    + joinPoint.getSignature().getName() + "()");
            throw e;
        }
    }

    private ObjectMapper getJSONMapper() {
        SimpleModule module = new SimpleModule();
        module.addSerializer(LocalDate.class, new LocalDateSerializer());
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);
        return mapper;
    }
}