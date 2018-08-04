package me.david.processinginjava.input;

import me.david.processinginjava.Application;
import me.david.processinginjava.exception.InputException;
import org.lwjgl.glfw.GLFWCursorPosCallbackI;

public class MousePositionHandler extends InputHandler implements GLFWCursorPosCallbackI {

    public MousePositionHandler(Application application) {
        super(application);
    }

    @Override
    public void invoke(long windowID, double xPos, double yPos) {
        if (!application.focused) return;

        application.mouseX = xPos;
        application.mouseY = yPos;

        if (application.mousePressed) {
            try {
                application.mouseDragged();
            } catch (Exception ex) {
                throw new InputException("Exception in mouseDragged() function", ex);
            }
        } else {
            try {
                application.mouseMoved();
            } catch (Exception ex) {
                throw new InputException("Exception in mouseMoved() function", ex);
            }
        }
    }
}
