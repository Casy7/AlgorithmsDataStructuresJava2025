package com.gravitypong.game.systems;

import com.gravitypong.game.controller.IPaddleController;
import com.gravitypong.game.model.Ball;
import com.gravitypong.game.model.Paddle;

import java.util.HashMap;
import java.util.Map;

public class PaddleControlSystem {
    private Map<Paddle, IPaddleController> controllers = new HashMap<>();

    public void register(Paddle paddle, IPaddleController controller) {
        controllers.put(paddle, controller);
    }

    public void update(Ball ball) {
        for (var entry : controllers.entrySet()) {
            Paddle paddle = entry.getKey();
            IPaddleController controller = entry.getValue();
            double dir = controller.getMoveDirection(paddle, ball);
            paddle.currentMoveDirection = dir;
        }
    }
}