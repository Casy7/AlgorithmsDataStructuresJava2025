package com.gravitypong.game.GravityStrategies;


import com.gravitypong.game.Vector2D;
import com.gravitypong.game.GameObjects.Ball;
import com.gravitypong.game.GravityStrategies.GravityStrategy;




public class CenterGravity implements GravityStrategy {
    private double strength;

    public CenterGravity(double strength) {
        this.strength = strength;
    }

    public Vector2D getGravityForce(Ball ball, double width, double height) {
        double centerX = width / 2;
        double centerY = height / 2;
        Vector2D force = new Vector2D(centerX - ball.pos.x, centerY - ball.pos.y);
        
        return force.normalize().multNew(strength);
    }
}