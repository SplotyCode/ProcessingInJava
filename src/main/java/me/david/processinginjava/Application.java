package me.david.processinginjava;

import lombok.Getter;
import me.david.processinginjava.exception.StartUpException;
import me.david.processinginjava.loop.LoopThread;
import me.david.processinginjava.utils.StartupHelper;

public class Application {

    @Getter private boolean loop = true, ruuning, setup;
    @Getter private short frames = 30;
    private static StartupHelper startupHelper = new StartupHelper();
    private LoopThread loopThread;

    protected void setup() throws Exception {}
    public void loop() throws Exception {}

    public static void launch(Application application) {
        if (startupHelper.isStartupCalled()) throw new StartUpException("Launch called twice");
        startupHelper.setStartupCalled(true);

        application.setup = true;
        try {
            application.setup();
        } catch (Exception ex) {
            throw new StartUpException("Exception in setup() method", ex);
        }
        application.setup = false;
        application.loopThread = new LoopThread(application);
        application.loopThread.start();
        application.ruuning = true;
    }

    protected void noLoop() {
        if (!setup) throw new StartUpException("noLoop() not called in setup()");
        if (!loop) throw new StartUpException("noLoop() called twice");
        loop = false;
    }



}
