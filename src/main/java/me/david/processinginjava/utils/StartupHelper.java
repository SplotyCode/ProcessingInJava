package me.david.processinginjava.utils;

import lombok.Getter;
import lombok.Setter;

public class StartupHelper {

    @Getter @Setter private boolean startupCalled;

    @Getter @Setter private short frames = 30;
    @Getter @Setter private boolean loop = true;




}
