package me.david.processinginjava.input;

import org.lwjgl.glfw.GLFW;

public enum MouseButton {

    LEFT(GLFW.GLFW_MOUSE_BUTTON_LEFT),
    RIGHT(GLFW.GLFW_MOUSE_BUTTON_RIGHT),
    MIDDLE(GLFW.GLFW_MOUSE_BUTTON_MIDDLE),
    OTHER(-1),
    NONE(-2);

    protected int button;

    MouseButton(int button) {
        this.button = button;
    }

    public static MouseButton fromButton(int button) {
        for (MouseButton mB : values()) {
            if (mB.button == button)
                return mB;
        }
        return OTHER;
    }

}
