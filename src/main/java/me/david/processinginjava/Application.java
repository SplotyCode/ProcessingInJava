package me.david.processinginjava;

import lombok.Getter;
import me.david.processinginjava.application.WindowHelper;
import me.david.processinginjava.exception.StartUpException;
import me.david.processinginjava.application.LoopThread;
import me.david.processinginjava.utils.StartupHelper;

import java.util.concurrent.atomic.AtomicBoolean;

public class Application {

    @Getter private boolean setup;
    @Getter private AtomicBoolean running = new AtomicBoolean(false);
    @Getter private StartupHelper startupHelper = new StartupHelper();
    private LoopThread loopThread;
    @Getter private WindowHelper windowHelper = new WindowHelper();

    protected void setup() throws Exception {}
    public void draw() throws Exception {}

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

        application.startupHelper.checkStart();
        application.windowHelper.start(application);

        application.loopThread = new LoopThread(application);
        application.loopThread.start();
    }

    protected void noLoop() {
        if (!setup) throw new StartUpException("noLoop() not called in setup()");
        if (!startupHelper.isLoop()) throw new StartUpException("noLoop() called twice");
        startupHelper.setLoop(false);
    }

    protected void fullScreen() {
        if (!setup) throw new StartUpException("noLoop() not called in setup()");
        if (!startupHelper.isFullscreen()) throw new StartUpException("noLoop() called twice");
        startupHelper.setFullscreen(true);
    }

    protected void frameRate(short fps) {
        if (!setup) throw new StartUpException("noLoop() not called in setup()");
        startupHelper.setFrames(fps);
    }

    protected void size(int width, int height) {
        if (!setup) throw new StartUpException("noLoop() not called in setup()");
        if (width < 1 || height < 1) throw new IllegalArgumentException("Invalid size: (width: " + width + " height: " + height + ")");

        startupHelper.setWidth(width);
        startupHelper.setHeight(height);
    }

}
