package com.wav.rzpp.exception;

import com.wav.rzpp.common.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sun.security.validator.ValidatorException;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: hbw
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /* 省略业务异常、运行时异常处理*/

    /**
     * 运行时异常
     *
     * @param e 捕获的异常类
     * @return 统一结果集
     */
    @ExceptionHandler(RuntimeException.class)
    public AjaxResult handleRunTimeException(RuntimeException e) {
        log.warn("运行时异常: {}", e.getMessage(), e);
        return AjaxResult.fail();
    }

    /**
     * ValidatorUtil校验异常
     *
     * @param e 捕获的异常
     * @return 统一结果集
     */
    @ExceptionHandler(ValidatorException.class)
    public AjaxResult handleValidatorException(ValidatorException e) {
        // 打印精确的参数错误日志，方便后端排查
        log.warn("参数校验异常: {}", e.getMessage(), e);
        // 一般来说，给客户端展示泛化的错误信息即可，联调时可以返回精确的信息
        return AjaxResult.failed(e.getMessage());
    }

    /**
     * @param e 接收到的异常
     * @return 结果链
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public AjaxResult handleConstraintViolationException(ConstraintViolationException e) {
        // 打印精确的参数错误日志，方便后端排查
        log.warn("参数校验异常: {}", e.getMessage(), e);
        // 一般来说，给客户端展示“参数错误”等泛化的错误信息即可，联调时可以返回精确的信息：e.getMessage()
        return AjaxResult.failed(e.getMessage());
    }

    /**
     * BindException异常
     *
     * @param e 捕获异常类
     * @return 统一结果集
     */
    @ExceptionHandler(BindException.class)
    public AjaxResult validationBindException(BindException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        String message = fieldErrors.stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(" && "));
        log.error("参数错误: {}", message, e);
        return AjaxResult.failed(message);
    }

    /**
     * MethodArgumentNotValidException异常
     *
     * @param e 捕获异常类
     * @return 统一结果集
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public AjaxResult validationMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        String message = fieldErrors.stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(" && "));
        log.error("参数错误: {}", message, e);
        return AjaxResult.failed(message);
    }
}
