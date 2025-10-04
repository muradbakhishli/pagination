package az.ingress.aspect;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class AspectLogging {

    @SneakyThrows
    @Around(value = "@annotation(ToLog)") // this is advice
    public Object logging(ProceedingJoinPoint jp){
        var arguments = jp.getArgs();
        var methodName = jp.getSignature().getName();
        log.info("ActionLong." + methodName + ".start - {}", arguments);
        var obj = jp.proceed();
        log.info("ActionLong." + methodName + ".end successfully");
        return obj;
    }
}
