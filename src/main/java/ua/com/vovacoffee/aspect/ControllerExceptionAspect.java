package ua.com.vovacoffee.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ControllerExceptionAspect {

    private static Logger logger = Logger.getLogger(ControllerExceptionAspect.class);

    @AfterThrowing(pointcut = "execution(* ua.com.vovacoffee..controller..*(..))", throwing = "exception")
    public void afterThrowingAdvice(Exception exception) {
        logger.error("EXCEPTION IN METHOD -> " + exception.getClass());
    }
}
