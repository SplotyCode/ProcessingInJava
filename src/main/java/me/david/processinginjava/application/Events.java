package me.david.processinginjava.application;

import me.david.processinginjava.Application;
import me.david.processinginjava.input.*;
import org.lwjgl.glfw.GLFW;

public class Events {

    public Events(long window, Application application) {
        /* Window Events/Callbacks */
        GLFW.glfwSetWindowFocusCallback(window, new WindowFocusHandler(application));
        GLFW.glfwSetWindowSizeCallback(window, new WindowResizeHandler(application));

        /* Mouse Related Events/Callbacks */
        GLFW.glfwSetMouseButtonCallback(window, new MouseButtonHandler(application));
        GLFW.glfwSetScrollCallback(window, new MouseScrollHandler(application));
        GLFW.glfwSetCursorPosCallback(window, new MousePositionHandler(application));

        /* Keyboard Events/Callbacks */
        GLFW.glfwSetKeyCallback(window, new KeyBoardHandler(application));

    }
}
