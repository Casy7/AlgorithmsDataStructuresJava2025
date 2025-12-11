package com.gravitypong.game.GameObjects;
import javafx.scene.paint.Color;

import javafx.scene.shape.Rectangle;

public class Paddle extends GameObject {
    private double speed;

    public Paddle(double x, double y, Color color, double speed) {
        super(x, y, 20, 100);
        this.speed = speed;
        
        Rectangle rect = new Rectangle(width, height, color);
        this.view = rect;
    }

    public void moveUp() { pos.y -= speed; }
    public void moveDown() { pos.y += speed; }
    
    // Screen clamp
    public void clamp(double screenHeight) {
        if (pos.y < 0) pos.y = 0;
        if (pos.y > screenHeight - height) pos.y = screenHeight - height;
    }
}