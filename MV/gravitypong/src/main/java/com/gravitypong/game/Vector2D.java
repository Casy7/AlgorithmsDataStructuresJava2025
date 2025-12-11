package com.gravitypong.game;


public class Vector2D {
    public double x, y;

    public Vector2D(double x, double y) { this.x = x; this.y = y; }

    public void add(Vector2D other) { this.x += other.x; this.y += other.y; }
    public void sub(Vector2D other) { this.x -= other.x; this.y -= other.y; }
    public void mult(double scalar) { this.x *= scalar; this.y *= scalar; }
    public Vector2D multNew(double scalar) { 
        return new Vector2D(this.x * scalar, this.y * scalar); 
    }
    
    // Vector length (magnitude)
    public double magnitude() { return Math.sqrt(x * x + y * y); }
    
    // Vector normalization
    public Vector2D normalize() {
        double mag = magnitude();
        return (mag == 0) ? new Vector2D(0, 0) : new Vector2D(x / mag, y / mag);
    }

    public Vector2D copy() { return new Vector2D(x, y); }
}