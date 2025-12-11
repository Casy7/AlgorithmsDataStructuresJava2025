package com.gravitypong.game;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import java.util.HashSet;
import java.util.Set;

import javafx.scene.input.KeyEvent;

public class InputHandler {
    private Set<KeyCode> activeKeys = new HashSet<>();

    public InputHandler(Scene scene) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, e -> activeKeys.add(e.getCode()));
        scene.addEventHandler(KeyEvent.KEY_RELEASED, e -> activeKeys.remove(e.getCode()));
    }

    public boolean isPressed(KeyCode key) {
        return activeKeys.contains(key);
    }
    
    public void clear() {
        activeKeys.clear();
    }
}