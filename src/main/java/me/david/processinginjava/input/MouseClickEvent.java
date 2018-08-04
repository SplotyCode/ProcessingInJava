package me.david.processinginjava.input;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class MouseClickEvent {

    @Getter private final int action, rawButton, mods;
    @Getter private final MouseButton button;

}
