package me.david.processinginjava;

import lombok.Getter;
import me.david.processinginjava.application.WindowHelper;
import me.david.processinginjava.exception.StartUpException;
import me.david.processinginjava.application.LoopThread;
import me.david.processinginjava.input.KeyBoardEvent;
import me.david.processinginjava.input.MouseButton;
import me.david.processinginjava.input.MouseClickEvent;
import me.david.processinginjava.utils.StartupHelper;

public class Application {

    @Getter private boolean setup;
    @Getter private StartupHelper startupHelper = new StartupHelper();
    private LoopThread loopThread;
    @Getter private WindowHelper windowHelper = new WindowHelper();
    @Getter private static Application instance;

    public long frameCount = 0;
    public boolean focused;
    public boolean mousePressed;
    public MouseButton mouseButton = MouseButton.NONE;
    public int mouseButtonInt;
    public double mouseX, mouseY;
    public double pmouseX, pmouseY;
    public boolean keyPressed;
    public int key;
    public char keyCode;

    protected void setup() throws Exception {}
    public void draw() throws Exception {}

    public void mouseReleased() throws Exception {}
    public void mousePressed() throws Exception {}
    public void mouseClicked() throws Exception {}
    public void mouseReleased(MouseClickEvent event) throws Exception {}
    public void mousePressed(MouseClickEvent event) throws Exception {}
    public void mouseClicked(MouseClickEvent event) throws Exception {}

    public void mouseWheel(double yOffset) throws Exception {}
    public void mouseDragged() throws Exception {}
    public void mouseMoved() throws Exception {}

    public void keyPressed() throws Exception {}
    public void keyReleased() throws Exception {}
    public void keyTyped() throws Exception {}
    public void keyPressed(KeyBoardEvent event) throws Exception {}
    public void keyReleased(KeyBoardEvent event) throws Exception {}
    public void keyTyped(KeyBoardEvent event) throws Exception {}

    public static void launch(Application application) {
        instance = application;
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

        application.loopThread = new LoopThread(application);
        application.loopThread.start();
    }

    protected void noLoop() {
        if (!setup) throw new StartUpException("noLoop() not called in setup()");
        if (!startupHelper.isLoop()) throw new StartUpException("noLoop() called twice");
        startupHelper.setLoop(false);
    }

    protected void fullScreen() {
        if (!setup) throw new StartUpException("fullscrren() not called in setup()");
        if (startupHelper.isFullscreen()) throw new StartUpException("fullscrren() called twice");
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
