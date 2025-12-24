package com.gravitypong.game.systems;

import com.gravitypong.game.model.Ball;
import com.gravitypong.game.model.Paddle;

public class CollisionSystem implements ICollisionSystem {

    @Override
    public void resolveCollisions(Ball ball, Paddle p1, Paddle p2, double width, double height) {
        resolveWallCollisions(ball, height);
        resolvePaddleCollision(ball, p1);
        resolvePaddleCollision(ball, p2);
    }

    private void resolveWallCollisions(Ball ball, double height) {
        if (ball.pos.y <= 0 || ball.pos.y >= height - ball.height) { // 20 = ball size
            ball.velocity.y *= -0.9;

            if (ball.pos.y < 0) ball.pos.y = 0;
            if (ball.pos.y > height - ball.height) ball.pos.y = height - 20;
        }
    }

    private void resolvePaddleCollision(Ball b, Paddle p) {
        if (b.pos.x < p.pos.x + p.width
                && b.pos.x + b.width > p.pos.x
                && b.pos.y < p.pos.y + p.height
                && b.pos.y + b.height > p.pos.y) {

            // Checking ball flight direction relative to the paddle center
            double ballCenter = b.pos.x + b.width / 2;
            double paddleCenter = p.pos.x + p.width / 2;

            // is the ball moving towards the paddle?
            boolean movingTowardsPaddle = (ballCenter < paddleCenter && b.velocity.x > 0) ||
                    (ballCenter > paddleCenter && b.velocity.x < 0);

            if (!movingTowardsPaddle) return;

            // Reversing velocity with slight boost
            b.velocity.x *= -1.03;
            b.velocity.y += (Math.random() - 0.5) * 2;

            // Pushing the ball out of the paddle to avoid sticking
            if (ballCenter < paddleCenter) {
                b.pos.x = p.pos.x - b.width - 1;
            } else {
                b.pos.x = p.pos.x + p.width + 1;
            }
        }
    }

    @Override
    public int checkGoal(Ball ball, double width) {
        if (ball.pos.x < 0) return 2;     // AI Scored
        if (ball.pos.x > width) return 1; // Player Scored
        return 0;
    }
}