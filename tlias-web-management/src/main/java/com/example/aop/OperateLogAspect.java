package com.example.aop;

import com.example.mapper.OperateLogMapper;
import com.example.pojo.LoginInfo;
import com.example.pojo.OperateLog;
import com.example.utils.CurrentHolder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class OperateLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.example.anno.Log)")
    public Object logOperation(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取当前登录用户ID，这里假设通过某种方式获取
        Integer operateEmpId = getCurrentUserId();

        // 记录开始时间
        LocalDateTime startTime = LocalDateTime.now();

        // 执行目标方法
        Object result = joinPoint.proceed();

        // 记录结束时间
        LocalDateTime endTime = LocalDateTime.now();
        long costTime = Duration.between(startTime, endTime).toMillis();

        // 获取目标方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();

        // 获取方法参数
        String methodParams = serializeObjects(joinPoint.getArgs());

        // 获取返回值
        String returnValue = serializeObject(result);

        // 构建日志对象
        OperateLog operateLog = new OperateLog();
        operateLog.setOperateEmpId(operateEmpId);
        operateLog.setOperateTime(startTime);
        operateLog.setClassName(className);
        operateLog.setMethodName(methodName);
        operateLog.setMethodParams(methodParams);
        operateLog.setReturnValue(returnValue);
        operateLog.setCostTime(costTime);

        // 保存日志到数据库
        log.info("日志记录：" + operateLog);
        operateLogMapper.insert(operateLog);

        return result;
    }

    // 辅助方法：序列化多个对象为JSON字符串
    private String serializeObjects(Object[] objects) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < objects.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(serializeObject(objects[i]));
        }
        return sb.toString();
    }

    // 辅助方法：序列化单个对象为JSON字符串
    private String serializeObject(Object object) {
        if (object == null) {
            return "null";
        }
        ObjectMapper objectMapper = new ObjectMapper();
        if (isSerializable(object)) {
            try {
                return objectMapper.writeValueAsString(object);
            } catch (JsonProcessingException e) {
                // 如果仍然出现异常，使用 toString 方法
                return object.toString();
            }
        } else {
            return object.toString();
        }
    }

    // 辅助方法：检查对象是否可序列化
    private boolean isSerializable(Object object) {
        Class<?> clazz = object.getClass();
        return clazz.isPrimitive() ||
                clazz.isArray() ||
                clazz.equals(String.class) ||
                clazz.equals(Integer.class) ||
                clazz.equals(Long.class) ||
                clazz.equals(Double.class) ||
                clazz.equals(Boolean.class) ||
                clazz.equals(Float.class) ||
                clazz.equals(Character.class) ||
                clazz.equals(Byte.class) ||
                clazz.equals(Short.class) ||
                clazz.equals(java.util.Date.class) ||
                clazz.equals(LocalDateTime.class) ||
                clazz.equals(LocalDate.class) ||
                clazz.equals(LocalTime.class);
    }

    // 假设的方法，用于获取当前登录用户ID
    private Integer getCurrentUserId() {
        // 这里为实际的用户信息获取逻辑
        return CurrentHolder.getCurrentId(); // 示例用户ID
    }
}



