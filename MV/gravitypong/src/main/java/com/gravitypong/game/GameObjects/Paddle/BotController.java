package com.gravitypong.game.GameObjects.Paddle;

package com.gravitypong.game.controller;

import com.gravitypong.game.model.Ball;
import com.gravitypong.game.model.Paddle;

public class BotController implements PaddleController {
    @Override
    public double getMoveDirection(Paddle paddle, Ball ball) {
        double paddleCenter = paddle.pos.y + paddle.height / 2;
        
        // Проста логіка: слідкувати за м'ячем з мертвою зоною (щоб не тремтів)
        if (ball.pos.y < paddleCenter - 10) return -1;
        if (ball.pos.y > paddleCenter + 10) return 1;
        return 0;
    }
}