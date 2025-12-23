package com.gravitypong.game.GameObjects;
package com.gravitypong.game.model;

import com.gravitypong.game.controller.PaddleController;

public class Paddle extends GameObject {
    public double speed;
    private PaddleController controller; // 👈 Залежність від АБСТРАКЦІЇ

    public Paddle(double x, double y, double speed, PaddleController controller) {
        super(x, y, 20, 100);
        this.speed = speed;
        this.controller = controller;
    }

    public void update(Ball ball, double screenHeight) {
        // Ракетка питає у контролера: "Куди мені йти?"
        double direction = controller.getMoveDirection(this, ball);
        
        pos.y += direction * speed;

        // Clamping (Логіка обмеження - це частина фізики/моделі)
        if (pos.y < 0) pos.y = 0;
        if (pos.y > screenHeight - height) pos.y = screenHeight - height;
    }
}