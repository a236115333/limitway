package com.limitway.current.redisUtils.aop;


import com.google.common.collect.ImmutableList;
import com.limitway.current.redisUtils.annotation.Limit;
import com.limitway.current.redisUtils.LimitType;
import com.sun.org.apache.bcel.internal.generic.IFEQ;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.lang.reflect.Method;

@Aspect
@Configuration
public class LimitInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LimitInterceptor.class);

    private static final String UNKNOWN = "unknown";

    private final RedisTemplate<String, Serializable> limitRedisTemplate;


    @Autowired
    public LimitInterceptor(RedisTemplate<String, Serializable> limitRedisTemplate) {
        this.limitRedisTemplate = limitRedisTemplate;
    }


    /**
     * @param pjp
     * @author fu
     * @description 切面
     * @date 2020/4/8 13:04
     */
    @Around("execution(public * *(..)) && @annotation(com.limitway.current.redisUtils.annotation.Limit)")
    public Object interceptor(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        Limit limitAnnotation = method.getAnnotation(Limit.class);
        LimitType limitType = limitAnnotation.limitType();
        String name = limitAnnotation.name();
        String key;
        int limitPeriod = limitAnnotation.period();
        int limitCount = limitAnnotation.count();

        /**
         * 根据限流类型获取不同的key ,如果不传我们会以方法名作为key
         */
        switch (limitType) {
            case IP:
                key = getIpAddress();
                break;
            case CUSTOMER:
                key = limitAnnotation.key();
                break;
            default:
                key = StringUtils.upperCase(method.getName());
        }
        //拼接前缀和key
        ImmutableList<String> keys = ImmutableList.of(StringUtils.join(limitAnnotation.prefix(), key));
        try {
            String luaScript = buildLuaScript();
            RedisScript<Long> redisScript = new DefaultRedisScript<>(luaScript, Long.class);
            Serializable a = limitRedisTemplate.opsForValue().get(key);
            long count = limitRedisTemplate.execute(redisScript, keys, limitCount, limitPeriod);
            logger.info("Access try count is {} for name={} and key = {}", count, name, key);
            if (count != 0 && count <= limitCount) {
                return pjp.proceed();
            } else {
                throw new RuntimeException("You have been dragged into the blacklist");
            }
        } catch (Throwable e) {
            if (e instanceof RuntimeException) {
                throw new RuntimeException(e.getLocalizedMessage());
            }
            throw new RuntimeException("server exception");
        }
    }

    /**
     * @author fu
     * @description 编写 redis Lua 限流脚本
     * @date 2020/4/8 13:24
     */
    public String buildLuaScript() {
        StringBuilder lua = new StringBuilder();
        lua.append("local c");
        lua.append("\nc = redis.call('get',KEYS[1])");
        // 调用不超过最大值，则直接返回
        lua.append("\nif c and tonumber(c) > tonumber(ARGV[1]) then");
        lua.append("\nreturn tonumber(c);");
        lua.append("\nend");
        // 执行计算器自加
        lua.append("\nc = redis.call('incr',KEYS[1])");
        lua.append("\nif tonumber(c) == 1 then");
        // 从第一次调用开始限流，设置对应键值的过期
        lua.append("\nredis.call('expire',KEYS[1],ARGV[2])");
        lua.append("\nend");
        lua.append("\nreturn c;");
        return lua.toString();
    }

    public String buildLua1(){
        StringBuilder lua = new StringBuilder();
        lua.append("local c");
        lua.append("\nc = redis.call('get',KEYS[1])");
        // 调用不超过最大值，则直接返回
        lua.append("\nreturn c;");
        return lua.toString();
    }


    /**
     * @author fu
     * @description 获取id地址
     * @date 2020/4/8 13:24
     */
    public String getIpAddress() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


//    @Around("execution(public * *(..)) && @annotation(com.limitway.current.redisUtils.annotation.Limit)")
//    public Object interptors(ProceedingJoinPoint poj){
//        MethodSignature signature = (MethodSignature)poj.getSignature();
//        Method method = signature.getMethod();
//        Limit limit = method.getAnnotation(Limit.class);
//        LimitType type = limit.limitType();
//        int period = limit.period();
//        int limitCount = limit.count();
//        String key;
//        switch (type){
//            case IP: key = getIpAddress(); break;
//            case CUSTOMER: key = limit.key(); break;
//            default:key = StringUtils.upperCase(method.getName());
//        }
//        ImmutableList<String>  immutableList = ImmutableList.of(StringUtils.join(limit.prefix(),key));
//        try {
//            String  luaScripe = buildLuaScript();
//            RedisScript<Long>  redisScript = new DefaultRedisScript<>(luaScripe,Long.class);
//            long count = limitRedisTemplate.execute(redisScript,immutableList,limitCount,period);
//            System.out.println(count);
//            if(count != 0 && count<=limitCount){
//                return poj.proceed();
//            }else {
//                return 1111;
//            }
//
//        }catch (Throwable e){
//            if (e instanceof RuntimeException) {
//                throw new RuntimeException(e.getLocalizedMessage());
//            }
//            throw new RuntimeException("server exception");
//        }
//
//    }

}

