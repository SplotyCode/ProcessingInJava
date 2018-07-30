package me.david.processinginjava;

import me.david.processinginjava.exception.StartUpException;
import me.david.processinginjava.utils.StartupHelper;

public class Application {

    private boolean loop = true;
    private static StartupHelper startupHelper = new StartupHelper();

    protected void setup() {}
    protected void loop() {}

    public static void launch() {
        if (startupHelper.isStartupCalled()) throw new StartUpException("Launch called twice");
        startupHelper.setStartupCalled(true);

        String appClazz = startupHelper.getApplicationClass();
        if (appClazz == null) throw new StartUpException("Count not find Application Class");

        try {
            Class clazz = Class.forName(appClazz, false, Thread.currentThread().getContextClassLoader());
            if (Application.class.isAssignableFrom(clazz)) {
                clazz.getMethod("setup").invoke(clazz);
            } else {
                throw new RuntimeException("Launching Class need to extend from Application!");
            }
        } catch (ClassNotFoundException ex) {
            throw new StartUpException("Count not find class from String: '" + appClazz + "'!", ex);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }

    protected void noLoop() {
        if (!loop) throw new StartUpException("noLoop() called twice");
        loop = false;
    }



}
