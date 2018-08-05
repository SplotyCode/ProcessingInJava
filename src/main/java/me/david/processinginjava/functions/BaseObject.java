package me.david.processinginjava.functions;

import org.lwjgl.opengl.GL11;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class BaseObject {

    public static int height, width;


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
        updateColor(stroke);
        GL11.glBegin(GL11.GL_LINE_STRIP);

        GL11.glVertex2d(x1, y1);
        GL11.glVertex2d(x2, y2);
        GL11.glEnd();
    }

    private static void updateColor(int number) {
        Color color = new Color(number);
        GL11.glColor4f(color.getRed(), color.getGreen(),  color.getBlue(), color.getAlpha());
    }

    public static void strokeWeight(int weight) {
        GL11.glLineWidth(weight);
    }

    public static void point(float x, float y) {
        glBegin(GL_POINTS);
        glVertex2f(x, y);
        glEnd();
    }

    public static void point(float x, float y, float z) {
        glBegin(GL_POINTS);
        glVertex3f(x, y, z);
        glEnd();
    }

    public static void triangle(float x1, float y1, float x2, float y2, float x3, float y3) {
        glBegin(GL_TRIANGLES);
        glVertex2f(x1, y1);
        glVertex2f(x2, y2);
        glVertex2f(x3, y3);
        glEnd();
    }

    public static void rect(float x, float y, float width, float height) {
        glBegin(GL_POINTS);
        glVertex2f(x, y);
        glVertex2f(x + width, y);
        glVertex2f(x, y + height);
        glVertex2f(x + width, y + height);
        glEnd();
    }

}
