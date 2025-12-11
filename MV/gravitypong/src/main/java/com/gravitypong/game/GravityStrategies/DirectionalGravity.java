package com.gravitypong.game.GravityStrategies;

import com.gravitypong.game.GameObjects.Ball;
import com.gravitypong.game.Vector2D;

public class DirectionalGravity implements GravityStrategy {
    private Vector2D force;

    public DirectionalGravity(double x, double y) {
        this.force = new Vector2D(x, y);
    }

    @Override
    public Vector2D getGravityForce(Ball ball, double width, double height) {
        return force;
    }
}