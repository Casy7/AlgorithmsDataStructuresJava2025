package com.gravitypong.game.gameconfig;
import com.gravitypong.game.gravitystrategies.GravityStrategy;

public class GameConfig {
    public static class GravityOption {
        public String name;
        public GravityStrategy strategy;

        public GravityOption(String name, GravityStrategy strategy) {
            this.name = name;
            this.strategy = strategy;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}