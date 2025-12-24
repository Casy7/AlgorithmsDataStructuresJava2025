package com.gravitypong.game.systems;

import com.gravitypong.game.model.Ball;
import com.gravitypong.game.model.Paddle;
import com.gravitypong.game.systems.MovementSystem;
import com.gravitypong.game.gravitystrategies.GravityStrategy;

public class MovementSystem implements IMovementSystem {
    private GravityStrategy gravityStrategy;

    @Override
    public void setGravityStrategy(GravityStrategy strategy) {
        this.gravityStrategy = strategy;
    }

    @Override
    public void update(Ball ball, Paddle p1, Paddle p2, double width, double height) {        // Adding gravity
        var gravity = gravityStrategy.getGravityForce(ball, width, height);
        ball.velocity.add(gravity);
        // Adding velocity
        ball.pos.add(ball.velocity);

        updatePaddle(p1, height);
        updatePaddle(p2, height);
    }


    private void updatePaddle(Paddle p, double height) {
        // Фізика: Позиція += Напрямок * Швидкість
        p.pos.y += p.currentMoveDirection * p.speed;

        // Обмеження (Clamping) - це теж частина фізики/руху
        if (p.pos.y < 0) p.pos.y = 0;
        if (p.pos.y > height - p.height) p.pos.y = height - p.height;
    }
}