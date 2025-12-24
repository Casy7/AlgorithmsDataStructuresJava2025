package com.gravitypong.game.systems;

import com.gravitypong.game.model.Ball;
import com.gravitypong.game.gravitystrategies.GravityStrategy;
import com.gravitypong.game.model.Paddle;

public interface IMovementSystem {
    void setGravityStrategy(GravityStrategy strategy);
    void update(Ball ball, Paddle p1, Paddle p2, double width, double height);
}