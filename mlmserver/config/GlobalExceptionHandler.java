package com.mlm.mlmserver.config;

import com.alibaba.fastjson2.JSONObject;
import com.mlm.mlmserver.entity.Response;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BindException.class)
    public Response<JSONObject> handlerBindException(BindException e) {
        JSONObject result = new JSONObject();
        e.getFieldErrors().forEach(fieldError -> {
            result.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return new Response<JSONObject>().error(400, "参数错误", result);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response<JSONObject> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        JSONObject result = new JSONObject();
        e.getFieldErrors().forEach(fieldError -> {
            result.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return new Response<JSONObject>().error(400, "参数错误", result);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Response<JSONObject> handleConstraintViolationException(ConstraintViolationException e) {
        System.out.println(222);
        // JSONObject result = new JSONObject();
        // e.
        //         getFieldErrors().forEach(fieldError -> {
        //     result.put(fieldError.getField(), fieldError.getDefaultMessage());
        // });
        // return new Response<JSONObject>().error(400, "参数错误", result);
        return new Response<JSONObject>().error(400, "参数错误", null);
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    public Response<Object> handlerBadSqlGrammarException(BadSqlGrammarException e) {
        // System.out.println(e.getMessage());
        return new Response<Object>().error(1, "sql语法错误");
    }

    @ExceptionHandler(CommunicationsException.class)
    public Response<Object> handlerCommunicationsException(CommunicationsException e) {
        // System.out.println("mysql数据库连接失败");
        // System.out.println(e.getMessage());
        return new Response<Object>().error(2, "mysql数据库连接失败");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public Response<Object> handlerAccessDeniedException(AccessDeniedException e) {
        return new Response<Object>().error(3, "文件拒绝访问");
    }

//    @ExceptionHandler(NullPointerException.class)
//    public Response<Object> handlerException(NullPointerException e) {
//        return new Response<Object>().error(1, e.getMessage());
//    }


//	// 前端（或接口攻击者）使用非法的@RequestBody请求接口，解析异常字段，并将错误日志降级
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Response<Object> validationBodyException(MethodArgumentNotValidException exception) {
//        BindingResult result = exception.getBindingResult();
//        StringBuilder errorMsg = new StringBuilder();
//        if (result.hasErrors()) {
//            List<ObjectError> errors = result.getAllErrors();
//            errors.forEach(p -> {
//                FieldError fieldError = (FieldError) p;
//                errorMsg.append(fieldError.getDefaultMessage()).append("!");
//                // 设置warn而不是error，日志错误降级
//                log.warn("Data check failure : object{" + fieldError.getObjectName() + "},field{" + fieldError.getField() + "},errorMessage{" + fieldError.getDefaultMessage() + "}");
//            });
//        }
//        return Response.error(errorMsg.toString());
//    }
}
