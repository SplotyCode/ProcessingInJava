package me.david.processinginjava.loop;

import lombok.AllArgsConstructor;
import me.david.processinginjava.Application;
import me.david.processinginjava.exception.TickException;

@AllArgsConstructor
public class LoopThread extends Thread {

    private Application application;

    @Override
    public void run() {
        if (application.isLoop()) {
            long lastLoop;
            long loopDelay = 1000 / application.getFrames();

            while (application.isRuuning()) {
                lastLoop = System.currentTimeMillis();
                callLoop();

                long delay = System.currentTimeMillis() - lastLoop;
                try {
                    sleep(loopDelay - delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else callLoop();
        System.out.println("Application Loop Thread stopped!");
    }

    private void callLoop() {
        try {
            application.loop();
        } catch (Exception ex) {
            throw new TickException("Exception in loop() method", ex);
        }
    }
}
