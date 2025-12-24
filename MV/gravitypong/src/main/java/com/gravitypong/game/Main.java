package com.gravitypong.game;

import com.gravitypong.game.gameconfig.*; // Імпортуємо наші Enums
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private Scene mainScene;
    private Pane gameRoot;
    private GameEngine gameEngine;

    // parameters by default
    private Difficulty selectedDifficulty = Difficulty.MEDIUM;
    private GravityOption selectedGravity = GravityOption.CLASSIC;
    private AiLevel selectedAiLevel = AiLevel.STANDARD;

    @Override
    public void start(Stage stage) {

        gameRoot = new Pane();
        mainScene = new Scene(new Pane(), WIDTH, HEIGHT);

        gameEngine = new GameEngine(gameRoot, mainScene, WIDTH, HEIGHT, this::showMenu);

        showMenu();

        stage.setTitle("Gravity Pong");
        stage.setScene(mainScene);
        stage.show();
    }

    private void showMenu() {
        VBox menuRoot = new VBox(20);
        menuRoot.setAlignment(Pos.CENTER);
        menuRoot.setStyle("-fx-background-color: #1a1a1a;");

        Text title = new Text("GRAVITY PONG");
        title.setFont(Font.font("Impact", 60));
        title.setFill(Color.CYAN);

        // --- Difficulty ---
        Label diffLabel = new Label("Difficulty:");
        diffLabel.setTextFill(Color.WHITE);
        ComboBox<Difficulty> diffBox = new ComboBox<>();
        diffBox.getItems().addAll(Difficulty.values()); // Enum values
        diffBox.setValue(selectedDifficulty);
        diffBox.setOnAction(e -> selectedDifficulty = diffBox.getValue());

        // --- Gravity ---
        Label gravLabel = new Label("Gravity mode:");
        gravLabel.setTextFill(Color.WHITE);
        ComboBox<GravityOption> gravBox = new ComboBox<>();
        gravBox.getItems().addAll(GravityOption.values()); // Enum values!
        gravBox.setValue(selectedGravity);
        gravBox.setOnAction(e -> selectedGravity = gravBox.getValue());

        // --- AI Level ---
        Label aiLabel = new Label("AI Level:");
        aiLabel.setTextFill(Color.WHITE);
        ComboBox<AiLevel> aiBox = new ComboBox<>();
        aiBox.getItems().addAll(AiLevel.values());
        aiBox.setValue(selectedAiLevel);
        aiBox.setOnAction(e -> selectedAiLevel = aiBox.getValue());

        // --- Start Button ---
        Button startBtn = new Button("Start Game");
        startBtn.setStyle("-fx-font-size: 20px; -fx-base: #00AA00; -fx-text-fill: white;");
        startBtn.setPrefWidth(200);
        startBtn.setOnAction(e -> startGame());

        menuRoot.getChildren().addAll(
                title,
                diffLabel, diffBox,
                gravLabel, gravBox,
                aiLabel, aiBox,
                startBtn
        );

        mainScene.setRoot(menuRoot);
    }

    private void startGame() {
        gameRoot.setStyle("-fx-background-color: #222;");

        // Gameplay entry point
        gameEngine.setup(selectedDifficulty, selectedGravity.getStrategy(), selectedAiLevel);

        mainScene.setRoot(gameRoot);
        gameRoot.requestFocus();
        gameEngine.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}