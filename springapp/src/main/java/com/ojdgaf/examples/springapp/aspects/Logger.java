package com.ojdgaf.examples.springapp.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.ProceedingJoinPoint;
import java.io.IOException;
import java.io.Writer;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
public class Logger {
    private Writer writer;
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public Logger(String fileName) throws IOException {
        this.writer = new FileWriter(createAbsolutePath(fileName), true);
    }

    @Pointcut("execution(* com.ojdgaf.examples.springapp.controllers.FacultyController.*(..))")
    public void anyMethodInFacultyController() {}

    @Around("anyMethodInFacultyController()")
    public Object measureExecutionTime(ProceedingJoinPoint point) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = point.proceed();
        long end = System.currentTimeMillis();

        writeMessage(point.getSignature().getName() + " -> " + (end - start) + " milliseconds of execution");

        return result;
    }

    @AfterThrowing(pointcut = "anyMethodInFacultyController()", throwing = "throwable")
    public void logException(JoinPoint point, Throwable throwable) {
        writeMessage(point.getSignature().getName() + " -> " + throwable);
    }

    private void writeMessage(String message) {
        try {
            writer.write(getCurrentTime() + " --- " + message + "\n");
            writer.flush();
        } catch (IOException e) {}
    }

    private String getCurrentTime() {
        return formatter.format(new Date());
    }

    private String createAbsolutePath(String fileName) {
        return System.getProperty("catalina.base") + "/logs/" + fileName;
    }

    public void destroy() {
        try {
            writer.close();
        } catch (IOException e) {
        }
    }
}
