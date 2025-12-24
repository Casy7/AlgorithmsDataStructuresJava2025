package com.gravitypong.game.controller;

import com.gravitypong.game.model.Ball;
import com.gravitypong.game.model.Paddle;

public class BotController implements IPaddleController {
    @Override
    public double getMoveDirection(Paddle paddle, Ball ball) {
        double paddleCenter = paddle.pos.y + paddle.height / 2;

        // This AI just follows the ball
        if (ball.pos.y < paddleCenter - paddle.width / 2) return -1;
        if (ball.pos.y > paddleCenter + paddle.width / 2) return 1;
        return 0;
    }
}