package com.gravitypong.game.gameconfig;

public enum AiLevel {
    NOOB("Noob (Errors)"),
    STANDARD("Standard (Reacts)"),
    PRO("Pro (Predicts)");

    final String label;
    AiLevel(String label) { this.label = label; }
    @Override public String toString() { return label; }
}