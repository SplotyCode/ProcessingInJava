package me.david.processinginjava.input;

import me.david.processinginjava.Application;
import me.david.processinginjava.exception.InputException;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWMouseButtonCallbackI;

public class MouseButtonHandler extends InputHandler implements GLFWMouseButtonCallbackI {

    public MouseButtonHandler(Application application) {
        super(application);
    }

    @Override
    public void invoke(long windowID, int button, int action, int mods) {
        if (action == GLFW.GLFW_RELEASE) {
            application.mousePressed = false;
            application.mouseButton = MouseButton.NONE;
            application.mouseButtonInt = 0;
            try {
                application.mouseReleased();
            } catch (Exception ex) {
                throw new InputException("Exception in mouseReleased() function", ex);
            }
            try {
                application.mouseClicked();
            } catch (Exception ex) {
                throw new InputException("Exception in mouseClicked() function", ex);
            }
        } else {
            application.mousePressed = true;
            switch (button) {
                case GLFW.GLFW_MOUSE_BUTTON_LEFT:
                    application.mouseButton = MouseButton.LEFT;
                    break;
                case GLFW.GLFW_MOUSE_BUTTON_RIGHT:
                    application.mouseButton = MouseButton.RIGHT;
                    break;
                case GLFW.GLFW_MOUSE_BUTTON_MIDDLE:
                    application.mouseButton = MouseButton.MIDDLE;
                    break;
                default:
                    application.mouseButton = MouseButton.OTHER;
                    break;
            }
            application.mouseButtonInt = button;

            try {
                application.mousePressed();
            } catch (Exception ex) {
                throw new InputException("Exception in mousePressed() function", ex);
            }
        }
    }
}
