// package com.mlm.mlmserver.config;
//
// import org.aspectj.lang.JoinPoint;
// import org.aspectj.lang.annotation.Aspect;
// import org.aspectj.lang.annotation.Before;
// import org.aspectj.lang.annotation.Pointcut;
// import org.aspectj.lang.reflect.MethodSignature;
// import org.springframework.stereotype.Component;
//
// import java.lang.reflect.Method;
// import java.lang.reflect.Parameter;
// import java.util.Arrays;
//
// /**
//  * 参数校验切面
//  */
// @Aspect
// @Component
// public class ValidateAspect {
//     // 定义切点，拦截所有带有@ValidateParam注解的方法
//     // @Pointcut("@annotation(com.mlm.mlmserver.config.ValidateParam)")
//     // public void validateMethods() {}
//
//     // 定义切点，拦截所有带有@ValidateParam注解的参数
//     @Pointcut("execution(public * *(.., @ValidateParam (*), ..))")
//     public void validateParams() {}
//
//     // 前置通知，在方法执行前进行参数校验
//     @Before("validateParams()")
//     public void before(JoinPoint joinPoint) {
//         MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//         Method method = signature.getMethod();
//
//         // 获取方法的参数值
//         Object[] args = joinPoint.getArgs();
//         // 获取方法的参数类型
//
//         Parameter[] parameters = signature.getMethod().getParameters();
//         // 获取注解的参数值
//         String[] params = signature.getMethod().getDeclaredAnnotation(ValidateParam.class).params();
//
//         System.out.println(Arrays.toString(signature.getParameterTypes()));
//         System.out.println(Arrays.toString(parameters));
//         System.out.println(Arrays.toString(args));
//         System.out.println(Arrays.toString(params));
//         // if (params.is) {
//         //
//         // } else {
//         //
//         // }
//
//
//         // 遍历所有参数
//         // for (int i = 0; i < parameters.length; i++) {
//         //     Parameter item = parameters[i];
//         //     System.out.println(item.getName());
//         //     System.out.println(item.getType());
//         //     System.out.println(item.getParameterizedType());
//         //     System.out.println(item.getDeclaredAnnotation(ValidateParam.class));
//         //     System.out.println(item.getAnnotation(ValidateParam.class));
//         //     // item.
//         //     // if (arg == null) {
//         //     //     continue;
//         //     // }
//         // }
//     }
// }
