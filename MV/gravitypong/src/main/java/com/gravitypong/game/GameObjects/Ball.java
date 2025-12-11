package com.gravitypong.game.GameObjects;

import com.gravitypong.game.Vector2D;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball extends GameObject {
    public Vector2D velocity;

    public Ball(double x, double y, double speed) {
        super(x, y, 20, 20); // r=10
        this.velocity = new Vector2D(speed, speed);
        
        Circle circle = new Circle(10, Color.YELLOW);
        this.view = circle;
    }
}