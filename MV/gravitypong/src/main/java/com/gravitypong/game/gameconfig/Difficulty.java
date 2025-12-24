package com.gravitypong.game.gameconfig;

public enum Difficulty {
    EASY(4.0, 3.0), 
    MEDIUM(6.0, 5.0),
    HARD(9.0, 8.0);

    public final double ballSpeed;
    public final double paddleSpeed;

    Difficulty(double ballSpeed, double paddleSpeed) {
        this.ballSpeed = ballSpeed;
        this.paddleSpeed = paddleSpeed;
    }
}