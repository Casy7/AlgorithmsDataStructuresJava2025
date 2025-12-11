package com.gravitypong.game.GravityStrategies;

import com.gravitypong.game.Vector2D;
import com.gravitypong.game.GameObjects.Ball;

public interface GravityStrategy {
    Vector2D getGravityForce(Ball ball, double width, double height);
}
