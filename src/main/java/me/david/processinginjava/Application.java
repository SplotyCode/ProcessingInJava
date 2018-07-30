package me.david.processinginjava;

import lombok.Getter;
import me.david.processinginjava.exception.StartUpException;
import me.david.processinginjava.loop.LoopThread;
import me.david.processinginjava.utils.StartupHelper;

import java.util.concurrent.atomic.AtomicBoolean;

public class Application {

    @Getter private boolean setup;
    @Getter private AtomicBoolean running = new AtomicBoolean(false);
    @Getter private StartupHelper startupHelper = new StartupHelper();
    private LoopThread loopThread;

    protected void setup() throws Exception {}
    public void loop() throws Exception {}

    public static void launch(Application application) {
        if (application.startupHelper.isStartupCalled()) throw new StartUpException("Launch called twice");
        application.startupHelper.setStartupCalled(true);

        application.setup = true;
        try {
            application.setup();
        } catch (Exception ex) {
            throw new StartUpException("Exception in setup() method", ex);
        }
        application.setup = false;
        application.loopThread = new LoopThread(application);
        application.loopThread.start();
    }

    protected void noLoop() {
        if (!setup) throw new StartUpException("noLoop() not called in setup()");
        if (!startupHelper.isLoop()) throw new StartUpException("noLoop() called twice");
        startupHelper.setLoop(false);
    }



}
