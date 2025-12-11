package com.gravitypong.game.GameObjects;

import com.gravitypong.game.Vector2D;

import javafx.scene.Node;

public abstract class GameObject {
    public Vector2D pos;
    protected Node view;
    public double width, height;

    public GameObject(double x, double y, double width, double height) {
        this.pos = new Vector2D(x, y);
        this.width = width;
        this.height = height;
    }

    public Node getView() { return view; }
    
    // GO renders itself
    public void render() {
        view.setTranslateX(pos.x);
        view.setTranslateY(pos.y);
    }
}