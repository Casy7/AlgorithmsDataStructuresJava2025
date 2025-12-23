package com.gravitypong.game.GameObjects.Paddle;

import com.gravitypong.game.model.Ball;
import com.gravitypong.game.model.Paddle;

public interface IPaddleController {
    // returns: -1 (up), 1 (down), 0 (halt)
    double getMoveDirection(Paddle paddle, Ball ball);
}