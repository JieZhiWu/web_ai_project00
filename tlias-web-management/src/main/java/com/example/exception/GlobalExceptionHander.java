package com.example.exception;

import com.example.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHander {

    @ExceptionHandler
    public Result handleException(Exception e) {
        log.error("服务器发生异常：{}", e.getMessage());
        return Result.error("服务器发生异常：" + e.getMessage());
    }

    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("服务器发生异常：{}", e.getMessage());
        String message = e.getMessage();
        if (message.contains("Duplicate entry")) {
            int i = message.indexOf("Duplicate entry");
            String errMsg = message.substring(i);
            String[] arr = errMsg.split(" ");
            String errCnt = arr[2].substring(arr[2].indexOf("'") + 1, arr[2].lastIndexOf("'"));
            String errPrg = arr[5].substring(arr[5].indexOf(".") + 1, arr[5].lastIndexOf("'") );
            return Result.error(errPrg + " : " +  errCnt + "已存在");
        }
        return Result.error("服务器发生异常：" + e.getMessage());
    }
}
