package me.david.processinginjava.input;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class KeyBoardEvent {

    @Getter private int key, scancode, action, mods;
    @Getter private String keyName;
    @Getter private char keyChar;

}
