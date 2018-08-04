package me.david.processinginjava.application;

import me.david.processinginjava.Application;
import me.david.processinginjava.exception.TickException;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

public class LoopThread extends Thread {

    private Application application;
    private int overload = 0;

    public LoopThread(Application application) {
        this.application = application;
    }

    @Override
    public void run() {
        application.getWindowHelper().start(application);
        if (application.getStartupHelper().isLoop()) {
            long lastLoop;
            long loopDelay = 1000 / application.getStartupHelper().getFrames();

            while (!application.getWindowHelper().shouldStop()) {
                lastLoop = System.currentTimeMillis();
                callLoop();

                long delay = System.currentTimeMillis() - lastLoop;
                try {
                    handleOverload(delay, loopDelay);
                    sleep(Math.max(0, loopDelay - delay));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else callLoop();
        System.out.println("Application Loop Thread stopped!");
    }

    private void handleOverload(long delay, long loopDelay) {
        if (delay > loopDelay) overload++;
        else overload = 0;
        if (overload == 20) {
            overload = 0;
            System.out.println("draw() function takes to long count not keep up the last 20 ticks!");
        }
    }

    private void callLoop() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        try {
            application.draw();
        } catch (Exception ex) {
            throw new TickException("Exception in draw() method", ex);
        }
        GLFW.glfwSwapBuffers(application.getWindowHelper().getWindow());
        GLFW.glfwPollEvents();
        application.frameCount++;
    }

}
