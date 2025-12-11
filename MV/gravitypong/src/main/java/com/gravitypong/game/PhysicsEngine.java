package com.gravitypong.game;

import com.gravitypong.game.GameObjects.Ball;
import com.gravitypong.game.GameObjects.Paddle;
import com.gravitypong.game.GravityStrategies.DirectionalGravity;
import com.gravitypong.game.GravityStrategies.GravityStrategy;

public class PhysicsEngine {

    private double width, height;
    private GravityStrategy gravityStrategy;

    public PhysicsEngine(double width, double height) {
        this.width = width;
        this.height = height;
        // Default: zero gravity
        this.gravityStrategy = new DirectionalGravity(0.0, 0.0);
    }

    public void setGravityStrategy(GravityStrategy strategy) {
        this.gravityStrategy = strategy;
    }

    public void update(Ball ball, Paddle player, Paddle ai) {
        // Gravity
        Vector2D gravityForce = gravityStrategy.getGravityForce(ball, width, height);
        ball.velocity.add(gravityForce);
        ball.pos.add(ball.velocity);

        // When ball hits the wall
        if (ball.pos.y <= 0 || ball.pos.y >= height - ball.height) {
            ball.velocity.y *= -0.9;
            if (ball.pos.y <= 0) {
                ball.pos.y = 1; 
            }else {
                ball.pos.y = height - ball.height - 1;
            }
        }

        // Checking paddle collisions
        checkCollision(ball, player);
        checkCollision(ball, ai);
    }

    private void checkCollision(Ball b, Paddle p) {
        if (b.pos.x < p.pos.x + p.width
                && b.pos.x + b.width > p.pos.x
                && b.pos.y < p.pos.y + p.height
                && b.pos.y + b.height > p.pos.y) {

            b.velocity.x *= -1.1; // Acceleration

            // Random bounce angle
            b.velocity.y += (Math.random() - 0.5) * 2;

            // Push the ball back
            if (b.velocity.x > 0) {
                b.pos.x = p.pos.x - b.width - 1;
            } 
            else {
                b.pos.x = p.pos.x + p.width + 1;
            }
        }
    }

    // Перевірка на гол
    public int checkGoal(Ball ball) {
        if (ball.pos.x < 0) {
            return 2; // AI забив

                }if (ball.pos.x > width) {
            return 1; // Гравець забив

                }return 0;
    }
}
