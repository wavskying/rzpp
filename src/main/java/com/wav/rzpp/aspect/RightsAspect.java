package com.wav.rzpp.aspect;

import com.alibaba.fastjson.JSONObject;
import com.wav.rzpp.utils.JWTUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: hbw
 *
 * 权限切面类，对所有Get、Post请求进行权限认证
 **/
//@Aspect
//@Component
public class RightsAspect {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postMapping() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void getMapping() {
    }

    @Pointcut("@annotation(com.wav.rzpp.annotation.ExcludeJwtVerify)")
    public void excludeJwtVerify() {
    }

    @Around("(postMapping() || getMapping() && !excludeJwtVerify())")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        //获取上下文request，获取token并解析
//        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = requestAttributes.getRequest();
//        String token = request.getHeader("X-Token");
//        if(token == null){
//            return ;
//        }
//        //获取参数并赋值
//        Object[] args = proceedingJoinPoint.getArgs();
//        for (Object arg : args) {
//            JSONObject claims = JWTUtil.decode(token);
//            Object userId = claims.get("id");
//            //对于不同的Vo类进行相应的赋值
//            if (arg instanceof FileVo) {
//                FileVo fileVo = (FileVo) arg;
//                fileVo.setUserId(String.valueOf(userId));
//            }
//            if (arg instanceof UserVo) {
//                UserVo userVo = (UserVo) arg;
//                userVo.setId(String.valueOf(userId));
//            }
//        }
//    }
        return null;
    }
}
