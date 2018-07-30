package me.david.processinginjava.application;

import me.david.processinginjava.Application;
import me.david.processinginjava.exception.TickException;

public class LoopThread extends Thread {

    private Application application;
    private int overload = 0;

    public LoopThread(Application application) {
        this.application = application;
    }

    @Override
    public void run() {
        application.getRunning().set(true);
        if (application.getStartupHelper().isLoop()) {
            long lastLoop;
            long loopDelay = 1000 / application.getStartupHelper().getFrames();

            while (application.getRunning().get()) {
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
        application.getRunning().set(false);
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
        try {
            application.draw();
        } catch (Exception ex) {
            throw new TickException("Exception in draw() method", ex);
        }
    }
}
