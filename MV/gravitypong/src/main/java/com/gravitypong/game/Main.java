package com.gravitypong.game;

import com.gravitypong.game.GameObjects.Ball;
import com.gravitypong.game.GameObjects.Paddle;
import com.gravitypong.game.GravityStrategies.CenterGravity;
import com.gravitypong.game.GravityStrategies.DirectionalGravity;
import com.gravitypong.game.GravityStrategies.GravityStrategy;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class Main extends Application {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private Stage primaryStage;
    private Scene mainScene;
    
    
    private Pane gameRoot;
    private Paddle player, ai;
    private Ball ball;
    private Text scoreText;
    private int playerScore = 0, aiScore = 0;
    
    
    private PhysicsEngine physics;
    private InputHandler input;
    private AnimationTimer gameTimer;
    private boolean isRunning = false;

    
    private Difficulty selectedDifficulty = Difficulty.MEDIUM;
    private GravityOption selectedGravity;

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        
        
        initGravityOptions(); 

        
        mainScene = new Scene(new Pane(), WIDTH, HEIGHT);
        
        
        showMenu();

        stage.setTitle("SOLID Gravity Pong");
        stage.setScene(mainScene);
        stage.show();
    }

    
    private void showMenu() {
        
        stopGame();

        VBox menuRoot = new VBox(20); 
        menuRoot.setAlignment(Pos.CENTER);
        menuRoot.setStyle("-fx-background-color: #1a1a1a;"); 

        
        Text title = new Text("GRAVITY PONG");
        title.setFont(Font.font("Impact", 60));
        title.setFill(Color.CYAN);

        
        Label diffLabel = new Label("Складність:");
        diffLabel.setTextFill(Color.WHITE);
        
        ComboBox<Difficulty> diffBox = new ComboBox<>();
        diffBox.getItems().addAll(Difficulty.values());
        diffBox.setValue(selectedDifficulty); 
        diffBox.setOnAction(e -> selectedDifficulty = diffBox.getValue());
        diffBox.setStyle("-fx-font-size: 16px; -fx-base: #444;");

        
        Label gravLabel = new Label("Режим Гравітації:");
        gravLabel.setTextFill(Color.WHITE);

        ComboBox<GravityOption> gravBox = new ComboBox<>();
        gravBox.getItems().addAll(gravityOptions);
        gravBox.setValue(selectedGravity); 
        gravBox.setOnAction(e -> selectedGravity = gravBox.getValue());
        gravBox.setStyle("-fx-font-size: 16px; -fx-base: #444;");

        
        Button startBtn = new Button("ПОЧАТИ ГРУ");
        startBtn.setStyle("-fx-font-size: 20px; -fx-base: #00AA00; -fx-text-fill: white; -fx-font-weight: bold;");
        startBtn.setPrefWidth(200);
        startBtn.setOnAction(e -> startGame());

        
        menuRoot.getChildren().addAll(title, diffLabel, diffBox, gravLabel, gravBox, startBtn);

        
        mainScene.setRoot(menuRoot);
    }

    
private void startGame() {
        gameRoot = new Pane();
        gameRoot.setPrefSize(WIDTH, HEIGHT);
        gameRoot.setStyle("-fx-background-color: #222;");

        physics = new PhysicsEngine(WIDTH, HEIGHT);
        physics.setGravityStrategy(selectedGravity.strategy);

        initGameWorld();

        // Створюємо хендлер
        input = new InputHandler(mainScene);

        // ❌ ВИДАЛЯЄМО ЦЕЙ ШМАТОК! Він ламав управління.
        /* mainScene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) { showMenu(); }
        });
        */
        
        // ✅ ВАЖЛИВО: Просимо фокус на ігрове поле, щоб клавіші точно спрацювали
        gameRoot.requestFocus(); 

        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!isRunning) return;
                handleInput();
                updatePhysics();
                render();
            }
        };
        
        isRunning = true;
        gameTimer.start();
        mainScene.setRoot(gameRoot);
    }
    private void stopGame() {
        isRunning = false;
        if (gameTimer != null) gameTimer.stop();
    }

    private void initGameWorld() {
        playerScore = 0; 
        aiScore = 0;

        player = new Paddle(30, HEIGHT / 2 - 50, Color.CYAN, selectedDifficulty.paddleSpeed);
        ai = new Paddle(WIDTH - 50, HEIGHT / 2 - 50, Color.HOTPINK, selectedDifficulty.paddleSpeed * 0.8);
        ball = new Ball(WIDTH / 2, HEIGHT / 2, selectedDifficulty.ballSpeed);

        scoreText = new Text(WIDTH / 2 - 50, 50, "0 : 0");
        scoreText.setFill(Color.WHITE);
        scoreText.setFont(Font.font(30));

        gameRoot.getChildren().addAll(scoreText, player.getView(), ai.getView(), ball.getView());
    }

private void handleInput() {
        if (input.isPressed(KeyCode.W)) player.moveUp();
        if (input.isPressed(KeyCode.S)) player.moveDown();
        
        if (input.isPressed(KeyCode.ESCAPE)) {
            showMenu();
        }
    }

    private void updatePhysics() {
        
        double aiCenter = ai.pos.y + ai.height / 2;
        if (ball.pos.y < aiCenter - 10) ai.moveUp();
        else if (ball.pos.y > aiCenter + 10) ai.moveDown();

        player.clamp(HEIGHT);
        ai.clamp(HEIGHT);

        physics.update(ball, player, ai);

        int goal = physics.checkGoal(ball);
        if (goal != 0) {
            if (goal == 1) playerScore++; else aiScore++;
            scoreText.setText(playerScore + " : " + aiScore);
            resetBall();
        }
    }

    private void render() {
        player.render();
        ai.render();
        ball.render();
    }

    private void resetBall() {
        ball.pos.x = WIDTH / 2;
        ball.pos.y = HEIGHT / 2;
        double dir = Math.random() > 0.5 ? 1 : -1;
        ball.velocity = new Vector2D(selectedDifficulty.ballSpeed * dir, (Math.random() - 0.5) * 5);
    }
    
    
    
    
    private static class GravityOption {
        String name;
        GravityStrategy strategy;

        GravityOption(String name, GravityStrategy strategy) {
            this.name = name;
            this.strategy = strategy;
        }

        @Override
        public String toString() { return name; } 
    }

    private java.util.List<GravityOption> gravityOptions;

    private void initGravityOptions() {
        gravityOptions = new java.util.ArrayList<>();
        gravityOptions.add(new GravityOption("Zero Gravity (Classic)", new DirectionalGravity(0, 0)));
        gravityOptions.add(new GravityOption("Earth Gravity (Down)", new DirectionalGravity(0, 0.25)));
        gravityOptions.add(new GravityOption("Windy Day (Right)", new DirectionalGravity(0.15, 0)));
        gravityOptions.add(new GravityOption("Black Hole (Center)", new CenterGravity(0.15)));
        
        
        selectedGravity = gravityOptions.get(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}