package me.david.processinginjava.input;

import me.david.processinginjava.Application;
import me.david.processinginjava.exception.InputException;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallbackI;

public class KeyBoardHandler extends InputHandler implements GLFWKeyCallbackI {

    private int keysPressed = 0;

    public KeyBoardHandler(Application application) {
        super(application);
    }

    @Override
    public void invoke(long windowID, int key, int scancode, int action, int mods) {
        System.out.println(action);
        if (action == GLFW.GLFW_RELEASE) {
            keysPressed--;
            try {
                application.keyReleased();
            } catch (Exception ex) {
                throw new InputException("Exception in keyReleased() function", ex);
            }
        } else if (action == GLFW.GLFW_PRESS) {
            keysPressed++;
            application.key = key;
            String name = GLFW.glfwGetKeyName(key, scancode);
            application.keyCode = name != null && !name.isEmpty() ? name.charAt(0) : null;
            try {
                application.keyPressed();
            } catch (Exception ex) {
                throw new InputException("Exception in keyPressed() function", ex);
            }
        } else {
            try {
                application.keyTyped();
            } catch (Exception ex) {
                throw new InputException("Exception in keyTyped() function", ex);
            }
        }

        application.keyPressed = keysPressed != 0;
    }
}
