package com.gravitypong.game.systems;

import com.gravitypong.game.model.Ball;
import com.gravitypong.game.model.Paddle;

public interface ICollisionSystem {
    void resolveCollisions(Ball ball, Paddle p1, Paddle p2, double width, double height);
    int checkGoal(Ball ball, double width);
}