package az.ingress.aspect;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ElapsedTimeLogger {

    @Pointcut(value = "execution(* az.ingress.service.abstraction.PaymentService.*(..))")
    public void elapsedTimePointCut(){
    }

    @SneakyThrows
    @Around(value = "elapsedTimePointCut()")
    public Object elapsedTimeLogger(ProceedingJoinPoint jp) {
        var startTime = System.currentTimeMillis();
        var response = jp.proceed();
        var endTime = System.currentTimeMillis();
        var elapsedTime = endTime - startTime;
        log.info("Elapsed time: {} ms", elapsedTime);
        return response;
    }
}
