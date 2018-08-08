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
        MouseClickEvent event = new MouseClickEvent(action, button, mods, MouseButton.fromButton(button));
        if (action == GLFW.GLFW_RELEASE) {
            application.mousePressed = false;
            application.mouseButton = MouseButton.NONE;
            application.mouseButtonInt = 0;
            try {
                application.mouseReleased();
                application.mouseReleased(event);
            } catch (Exception ex) {
                throw new InputException("Exception in mouseReleased() function", ex);
            }
        } else if (action == GLFW.GLFW_PRESS) {
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
                application.mousePressed(event);
            } catch (Exception ex) {
                throw new InputException("Exception in mousePressed() function", ex);
            }
        } else {
            try {
                application.mouseClicked();
                application.mouseClicked(event);
            } catch (Exception ex) {
                throw new InputException("Exception in mouseClicked() function", ex);
            }
        }
    }
}
