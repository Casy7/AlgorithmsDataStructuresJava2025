package com.gravitypong.game.gameobjects;

import com.gravitypong.game.Vector2D;

public abstract class GameObject {
    public Vector2D pos;
    public double width, height;

    public GameObject(double x, double y, double width, double height) {
        this.pos = new Vector2D(x, y);
        this.width = width;
        this.height = height;
    }
}