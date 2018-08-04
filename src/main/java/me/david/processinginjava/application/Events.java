package me.david.processinginjava.application;

import me.david.processinginjava.Application;
import me.david.processinginjava.exception.InputException;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

public class Events {

    public Events(long window, Application application) {
        GLFW.glfwSetWindowFocusCallback(window, (windowHnd, focused) -> application.focused = focused);
        GLFW.glfwSetWindowSizeCallback(window, (windowID, width, height) -> {
            application.getStartupHelper().setHeight(height);
            application.getStartupHelper().setWidth(width);

            GL11.glViewport(0, 0, application.getStartupHelper().getWidth(), application.getStartupHelper().getHeight());
            GL11.glMatrixMode(GL11.GL_PROJECTION);
            GL11.glLoadIdentity();
            GL11.glOrtho(0, application.getStartupHelper().getWidth(), 0, application.getStartupHelper().getHeight(), 1, -1);
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            GL11.glLoadIdentity();
        });
        GLFW.glfwSetKeyCallback(window, (windowID, key, scancode, action, mods) -> {

        });
        GLFW.glfwSetMouseButtonCallback(window, (windowID, button, action, mods) -> {
            if (action == GLFW.GLFW_RELEASE) {
                application.mousePressed = false;
                try {
                    application.mouseClicked();
                } catch (Exception ex) {
                    throw new InputException("Exception in mouseClicked() function", ex);
                }
            } else {
                application.mousePressed = true;
            }
        });
    }
}
