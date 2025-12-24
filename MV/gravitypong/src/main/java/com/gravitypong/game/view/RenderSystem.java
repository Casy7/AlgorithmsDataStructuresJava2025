package com.gravitypong.game.view;

import com.gravitypong.game.gameobjects.GameObject;
import com.gravitypong.game.model.Ball;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import java.util.HashMap;
import java.util.Map;

public class RenderSystem implements IRenderSystem {
    private Pane root;
    private Map<GameObject, Node> viewMap = new HashMap<>();

    public RenderSystem(Pane root) {
        this.root = root;
    }

    // Registers an object
    @Override
    public void register(GameObject obj, Color color) {
        Node view;
        if (obj instanceof Ball) {
            view = new Circle(obj.width / 2, color); // Radius = width/2
        } else {
            view = new Rectangle(obj.width, obj.height, color);
        }

        viewMap.put(obj, view);
        root.getChildren().add(view);
    }

    // Updates positions of visual nodes
    @Override
    public void update() {
        for (var entry : viewMap.entrySet()) {
            GameObject model = entry.getKey();
            Node view = entry.getValue();

            view.setTranslateX(model.pos.x);
            view.setTranslateY(model.pos.y);
        }
    }
}