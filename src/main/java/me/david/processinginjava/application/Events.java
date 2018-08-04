package me.david.processinginjava.application;

import me.david.processinginjava.Application;
import org.lwjgl.glfw.GLFW;

public class Events {

    public Events(long window, Application application) {
        GLFW.glfwSetWindowFocusCallback(window, (windowHnd, focused) -> application.focused = focused);
    }
}
