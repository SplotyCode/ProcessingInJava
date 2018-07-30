package me.david.processinginjava.utils;

import lombok.Getter;
import lombok.Setter;
import me.david.processinginjava.exception.CanNotStartException;

public class StartupHelper {

    @Getter @Setter private boolean startupCalled;

    @Getter @Setter private short frames = 30;
    @Getter @Setter private boolean loop = true, fullscreen;

    @Getter @Setter private int width = -1, height = -1;

    public void checkStart() {
        if (width == -1 || height == -1) throw new CanNotStartException("size() method not called!");
    }

    public void createWinow() {

    }


}
