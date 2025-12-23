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
        // collision check
        if (b.pos.x < p.pos.x + p.width
                && b.pos.x + b.width > p.pos.x
                && b.pos.y < p.pos.y + p.height
                && b.pos.y + b.height > p.pos.y) {

            // checking ball flight direction
            double ballCenter = b.pos.x + b.width / 2;
            double paddleCenter = p.pos.x + p.width / 2;

            
            boolean movingTowardsPaddle = (ballCenter < paddleCenter && b.velocity.x > 0) || 
                                          (ballCenter > paddleCenter && b.velocity.x < 0);
            
            if (!movingTowardsPaddle) return; 

            b.velocity.x *= -1.03; 
            b.velocity.y += (Math.random() - 0.5) * 2;

            // pushing the ball out of the paddle
            if (ballCenter < paddleCenter) {
                b.pos.x = p.pos.x - b.width - 1;
            } else {
                b.pos.x = p.pos.x + p.width + 1;
            }
        }
    }

    // Does the ball hit the goal?
    public int checkGoal(Ball ball) {
        if (ball.pos.x < 0) {
            return 2; // AI забив

                }if (ball.pos.x > width) {
            return 1; // Гравець забив

                }return 0;
    }
}
