package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect // 이것을 붙여주어야 AOP로 사용 가능하다.
@Component // Component Scan 사용
public class TimeTraceAop { // SpringConfig 에 설정하여 스프링 빈으로 등록한다. component scan을 사용해도 된다.

    @Around("execution(* hello.hellospring..*(..))") // 어디에 적용할지 타겟팅을 적용한다. --> 패키지 하위에 다 적용한 것
    public Object execut(ProceedingJoinPoint joinPoint) throws  Throwable{
        long start = System.currentTimeMillis();
        System.out.println("START : " + joinPoint.toString()); // 어떤 메소드를 call 했는지
        try {
            // 다음 메소드를 진행
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");

        }
    }
}
