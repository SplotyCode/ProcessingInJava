package me.david.processinginjava.functions;

import me.david.processinginjava.Application;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class BaseObject {

    private static int fill = 1, stroke = 1;

    public static void fill(int color) {
        fill = color;
    }

    public static void stroke(int color) {
        stroke = color;
    }

    public static void background(int number) {
        Color color = new Color(number);
        GL11.glClearColor(color.getRed(), color.getGreen(),  color.getBlue(), color.getAlpha());
    }

    public static void elipse(float x, float y, float xRadius, float yRadius) {
        updateColor(fill);
        GL11.glBegin(GL11.GL_LINE);
        for(int i=0; i < 360; i++) {
            //convert degrees into radians
            double degInRad = Math.toRadians(i);
            GL11.glVertex2f((float) Math.cos(degInRad) * xRadius + x,(float) Math.sin(degInRad) * yRadius + y);
        }
        GL11.glEnd();
    }

    public static void line(float x1, float y1, float x2, float y2) {
        GL11.glLineWidth(25);
        GL11.glColor3f(0.0f, 1.0f, 0.2f);
        GL11.glBegin(GL11.GL_LINE_STRIP);

        GL11.glVertex2d(x1, y1);
        GL11.glVertex2d(x2, y2);
        GL11.glEnd();
    }

    private static void updateColor(int number) {
        Color color = new Color(number);
        GL11.glColor4f(color.getRed(), color.getGreen(),  color.getBlue(), color.getAlpha());
    }

}
