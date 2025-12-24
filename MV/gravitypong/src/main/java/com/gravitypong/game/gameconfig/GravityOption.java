package com.gravitypong.game.gameconfig;
import com.gravitypong.game.gravitystrategies.*;

public enum GravityOption {
    CLASSIC("Zero Gravity (Classic)", new DirectionalGravity(0, 0)),
    EARTH("Earth Gravity (Down)", new DirectionalGravity(0, 0.2)),
    WIND("Windy Day (Left)", new DirectionalGravity(-0.05, 0)),
    HOLE("White Hole (Center)", new CenterGravity(-0.15));

    private final String label;
    private final GravityStrategy strategy;

    GravityOption(String label, GravityStrategy strategy) {
        this.label = label;
        this.strategy = strategy;
    }

    public GravityStrategy getStrategy() {
        return strategy;
    }

    @Override
    public String toString() {
        return label;
    }
}