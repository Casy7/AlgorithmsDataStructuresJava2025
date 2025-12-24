package com.gravitypong.game.view;

import com.gravitypong.game.gameobjects.GameObject;
import javafx.scene.paint.Color;

public interface IRenderSystem {
    void register(GameObject obj, Color color);
    void update();
}