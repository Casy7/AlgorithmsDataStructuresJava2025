package com.gravitypong.game.model;
import com.gravitypong.game.gameobjects.GameObject;

public class Paddle extends GameObject {
    public double speed;
    public double currentMoveDirection = 0;

    public Paddle(double x, double y, double speed) {
        super(x, y, 20, 100);
        this.speed = speed;
    }
}