import me.david.processinginjava.Application;

public class HelloWorld extends Application {

    public static void main(String[] args) {
        launch(new HelloWorld());
    }

    @Override
    protected void setup() throws Exception {
        size(200, 200);
        System.out.println("setup!!");
        frameRate((short) 30);
    }

    private long lastLoop;

    @Override
    public void draw() throws Exception {
        System.out.println((System.currentTimeMillis() - lastLoop) + "ms delay");
        lastLoop = System.currentTimeMillis();
        Thread.sleep(200);
    }
}
