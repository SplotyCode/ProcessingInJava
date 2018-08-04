package me.david.processinginjava.input;

import me.david.processinginjava.Application;
import org.lwjgl.glfw.GLFWWindowFocusCallbackI;


public class WindowFocusHandler extends InputHandler implements GLFWWindowFocusCallbackI {

    public WindowFocusHandler(Application application) {
        super(application);
    }

    @Override
    public void invoke(long windowID, boolean focused) {
        application.focused = focused;
    }
}
