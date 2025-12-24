package com.gravitypong.game.controller;

import com.gravitypong.game.model.Ball;
import com.gravitypong.game.model.Paddle;
import java.util.Random;

public class BotNoobController implements IPaddleController {
    private Random random = new Random();
    private int mistakeTimer = 0;
    private double errorOffset = 0;

    @Override
    public double getMoveDirection(Paddle paddle, Ball ball) {
        // This AI follows the ball + makes mistakes
        if (mistakeTimer-- <= 0) {
            mistakeTimer = 60;
            errorOffset = (random.nextDouble() - 0.5) * 80; // mistake range
        }

        // Some random movement goal
        if (random.nextDouble() < 0.08) {
            return 0;
        }

        double paddleCenter = paddle.pos.y + paddle.height / 2;
        double targetY = ball.pos.y + errorOffset;

        if (targetY < paddleCenter - paddle.width / 2 - 5) return -1;
        if (targetY > paddleCenter + paddle.width / 2 + 5) return 1;
        return 0;
    }
}
