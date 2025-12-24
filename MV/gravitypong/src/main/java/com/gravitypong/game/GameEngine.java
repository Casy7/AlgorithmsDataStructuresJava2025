package com.gravitypong.game;

import com.gravitypong.game.controller.*;
import com.gravitypong.game.gameconfig.Difficulty;
import com.gravitypong.game.model.*;
import com.gravitypong.game.systems.*;
import com.gravitypong.game.view.*;
import com.gravitypong.game.gravitystrategies.GravityStrategy;
import com.gravitypong.game.gameconfig.AiLevel;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameEngine {
    private final int width;
    private final int height;
    private final Pane root;
    private final Runnable onExit; // Callback to close Main

    private IMovementSystem movementSystem;
    private ICollisionSystem collisionSystem;
    private PaddleControlSystem paddleControlSystem;
    private IRenderSystem renderSystem;
    private InputHandler input;

    private Paddle player, ai;
    private Ball ball;
    private Text scoreText;
    private int playerScore = 0, aiScore = 0;

    private AnimationTimer gameTimer;
    private boolean isRunning = false;

    private Difficulty difficulty;
    private GravityStrategy gravity;
    private AiLevel aiLevel;

    public GameEngine(Pane root, Scene scene, int w, int h, Runnable onExit) {
        this.root = root;
        this.width = w;
        this.height = h;
        this.onExit = onExit;
        this.input = new InputHandler(scene);
    }

    public void setup(Difficulty difficulty, GravityStrategy gravity, AiLevel aiLevel) {
        this.difficulty = difficulty;
        this.gravity = gravity;
        this.aiLevel = aiLevel;

        // 1. Systems
        movementSystem = new MovementSystem();
        movementSystem.setGravityStrategy(gravity);
        collisionSystem = new CollisionSystem();
        renderSystem = new RenderSystem(root);

        // 2. Input
        input.clear();

        // 3. Controllers
        IPaddleController humanCtrl = new PlayerController(input);
        IPaddleController botCtrl = createBotController(aiLevel);

        // 4. Models
        player = new Paddle(30, height / 2 - 50, difficulty.paddleSpeed);
        ai = new Paddle(width - 50, height / 2 - 50, difficulty.paddleSpeed * 0.8);
        ball = new Ball(width / 2, height / 2, difficulty.ballSpeed);
        paddleControlSystem = new PaddleControlSystem();
        paddleControlSystem.register(player, humanCtrl);
        paddleControlSystem.register(ai, botCtrl);

        // 5. Register Views
        renderSystem.register(player, Color.CYAN);
        renderSystem.register(ai, Color.HOTPINK);
        renderSystem.register(ball, Color.YELLOW);

        // 6. Score UI
        initScoreBoard();
    }

    public void start() {
        if (isRunning) return;

        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        gameTimer.start();
        isRunning = true;
    }

    public void stop() {
        if (gameTimer != null) gameTimer.stop();
        isRunning = false;
        root.getChildren().clear();
    }

    private void update() {

        // Escape check
        if (input.isPressed(KeyCode.ESCAPE)) {
            stop();
            onExit.run();
            return;
        }

        paddleControlSystem.update(ball);

        movementSystem.update(ball, player, ai, width, height);
        collisionSystem.resolveCollisions(ball, player, ai, width, height);


        checkGoals();
        renderSystem.update();
    }

    private IPaddleController createBotController(AiLevel level) {
        switch (level) {
            case NOOB: return new BotNoobController();
            case PRO: return new BotProController(gravity, width, height);
            default: return new BotController();
        }
    }

    private void checkGoals() {
        int goalState = collisionSystem.checkGoal(ball, width);
        if (goalState != 0) {
            if (goalState == 1) playerScore++; else aiScore++;
            scoreText.setText(playerScore + " : " + aiScore);
            resetBall();
        }
    }

    private void resetBall() {
        ball.pos.x = width / 2;
        ball.pos.y = height / 2;
        double dir = Math.random() > 0.5 ? 1 : -1;
        ball.velocity.x = difficulty.ballSpeed * dir;
        ball.velocity.y = (Math.random() - 0.5) * 5;
    }

    private void initScoreBoard() {
        playerScore = 0;
        aiScore = 0;
        scoreText = new Text(width / 2 - 50, 50, "0 : 0");
        scoreText.setFill(Color.WHITE);
        scoreText.setFont(Font.font(30));
        root.getChildren().add(scoreText);
    }
}