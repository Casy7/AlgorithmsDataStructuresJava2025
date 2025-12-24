package com.gravitypong.game.gravitystrategies;

import com.gravitypong.game.Vector2D;
import com.gravitypong.game.model.Ball;

public interface GravityStrategy {
    Vector2D getGravityForce(Ball ball, double width, double height);
}
