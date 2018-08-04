import me.david.processinginjava.Application;

import java.awt.*;

import static me.david.processinginjava.functions.BaseObject.*;

public class HelloWorld extends Application {

    public static void main(String[] args) {
        launch(new HelloWorld());
    }

    @Override
    protected void setup() throws Exception {
        size(200, 200);
        System.out.println("setup!!");
        frameRate((short) 8);
    }

    private long lastLoop;

    @Override
    public void draw() throws Exception {
        //System.out.println((System.currentTimeMillis() - lastLoop) + "ms delay");
        //lastLoop = System.currentTimeMillis();
        //Thread.sleep(200);
        background(Color.CYAN.getRGB());
        stroke(2);
        line(2, 2, 40, 40);
    }
}
