package me.david.processinginjava.utils;

import lombok.Getter;
import lombok.Setter;
import me.david.processinginjava.Application;

public class StartupHelper {

    @Getter @Setter private boolean startupCalled;

    public String getApplicationClass() {
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        boolean found = false;
        String applicationClass = null;

        for (StackTraceElement traceElement : trace) {
            String clazz = traceElement.getClassName();
            String method = traceElement.getMethodName();
            if (found) {
                applicationClass = clazz;
                break;
            }

            if (Application.class.getName().equals(clazz) && "launch".equals(method)) {
                found = true;
            }
        }
        return applicationClass;
    }


}
