package me.david.processinginjava.input;

import me.david.processinginjava.Application;
import me.david.processinginjava.exception.InputException;
import org.lwjgl.glfw.GLFWScrollCallbackI;

public class MouseScrollHandler extends InputHandler implements GLFWScrollCallbackI {

    public MouseScrollHandler(Application application) {
        super(application);
    }

    @Override
    public void invoke(long windowID, double xOffset, double yOffset) {
        if (yOffset != 0)
            try {
                application.mouseWheel(yOffset);
            } catch (Exception ex) {
                throw new InputException("Exception in mouseWheel() function", ex);
            }
    }
}
