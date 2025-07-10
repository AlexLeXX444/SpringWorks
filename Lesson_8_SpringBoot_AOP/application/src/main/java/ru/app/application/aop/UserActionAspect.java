package ru.app.application.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class UserActionAspect {

    @Before("@annotation(ru.app.application.aop.annotation.TrackUserAction)")
    public void logUserAction(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        String username = getCurrentUsername();

        System.out.println("--------------------------------------------------");
        System.out.println("Пользователь: " + username);
        System.out.println("Вызван метод: " + methodName);
        System.out.println("Аргументы: " + Arrays.toString(args));
        System.out.println("--------------------------------------------------");
    }

    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        }
        return "SYSTEM";
    }
}
