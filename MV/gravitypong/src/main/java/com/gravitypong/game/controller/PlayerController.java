package com.gravitypong.game.controller;

import com.gravitypong.game.InputHandler;
import com.gravitypong.game.model.Ball;
import com.gravitypong.game.model.Paddle;
import javafx.scene.input.KeyCode;

public class PlayerController implements IPaddleController {
    private InputHandler input;

    public PlayerController(InputHandler input) {
        this.input = input;
    }

    @Override
    public double getMoveDirection(Paddle paddle, Ball ball) {
        if (input.isPressed(KeyCode.W)) return -1;
        if (input.isPressed(KeyCode.S)) return 1;
        return 0;
    }
}