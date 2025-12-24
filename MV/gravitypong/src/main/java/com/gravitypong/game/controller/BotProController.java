package com.gravitypong.game.controller;

import com.gravitypong.game.model.Ball;
import com.gravitypong.game.model.Paddle;
import com.gravitypong.game.Vector2D;
import com.gravitypong.game.gravitystrategies.GravityStrategy;

public class BotProController implements IPaddleController {
    private GravityStrategy gravityStrategy;
    private double fieldWidth;
    private double fieldHeight;

    public BotProController(GravityStrategy gravityStrategy, double width, double height) {
        this.gravityStrategy = gravityStrategy;
        this.fieldWidth = width;
        this.fieldHeight = height;
    }

    @Override
    public double getMoveDirection(Paddle paddle, Ball ball) {
        // This master piece of sh*t makes the FULL TRAJECTORY PREDICTION

        // If the ball is moving away from the bot it returns to the center position.
        if (ball.velocity.x < 0) {
            double paddleCenter = paddle.pos.y + paddle.height / 2;
            if (paddleCenter < fieldHeight / 2 - paddle.width / 2) return 1;
            if (paddleCenter > fieldHeight / 2 + paddle.width / 2) return -1;
            return 0;
        }

        // --- PREDICTION SYSTEM (!) ---
        Vector2D simPos = ball.pos.copy();
        Vector2D simVel = ball.velocity.copy();

        // Simulate flight until the ball reaches the paddle's X coordinate.
        // (Limit loop to 300 steps to prevent freezing if the ball gets stuck).
        int steps = 0;
        while (simPos.x < paddle.pos.x && steps < 300) {
            Ball tempBall = new Ball(simPos.x, simPos.y, 0); // Speed is irrelevant for force calculation
            Vector2D gravity = gravityStrategy.getGravityForce(tempBall, fieldWidth, fieldHeight);

            simVel.add(gravity);
            simPos.add(simVel);

            // Wall bounce
            if (simPos.y <= 0 || simPos.y >= fieldHeight - 20) {
                simVel.y *= -0.9;
                if (simPos.y < 0) simPos.y = 0;
                if (simPos.y > fieldHeight - 20) simPos.y = fieldHeight - 20;
            }

            steps++;
        }

        double targetY = simPos.y;
        double paddleCenter = paddle.pos.y + paddle.height / 2;

        if (targetY < paddleCenter - 5) return -1;
        if (targetY > paddleCenter + 5) return 1;
        return 0;
    }
}