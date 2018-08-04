package me.david.processinginjava.application;

import me.david.processinginjava.Application;
import me.david.processinginjava.exception.InputException;
import me.david.processinginjava.input.*;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

public class Events {

    public Events(long window, Application application) {
        GLFW.glfwSetWindowFocusCallback(window, new WindowFocusHandler(application));
        GLFW.glfwSetWindowSizeCallback(window, new WindowResizeHandler(application));
        GLFW.glfwSetKeyCallback(window, (windowID, key, scancode, action, mods) -> {

        });
        GLFW.glfwSetMouseButtonCallback(window, new MouseButtonHandler(application));
        GLFW.glfwSetScrollCallback(window, new MouseScrollHandler(application));
    }
}
