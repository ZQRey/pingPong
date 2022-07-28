package com.zqrey.pingpong;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class Pong extends Application {

    private static final int width = 800;
    private static final int height = 600;
    private static final int PLAYER_HEIGHT = 100;
    private static final int PLAYER_WIDHT = 15;
    private static final double BALL_R = 15;
    private int ballYSpeed = 1;
    private int ballXSpeed = 1;
    private double playerOneYPos = height / 2;
    private double playerTwoYPos = height / 2;
    private double ballXPos = width / 2;
    private double ballYPos = width / 2;
    private int scopeP1 = 0;
    private int scopeP2 = 0;
    private boolean gameStarted;
    private int playerOneXPos = 0;
    private double playerTwoXPos = width - PLAYER_WIDHT;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("PING PONG");
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Timeline tl = new Timeline(new KeyFrame(Duration.millis((10)), e -> run(gc)));
        tl.setCycleCount(Timeline.INDEFINITE);

        //mouse control
        canvas.setOnMouseMoved(e -> playerOneYPos = e.getY());
        canvas.setOnMouseClicked(e -> gameStarted = true);
        stage.setScene(new Scene(new StackPane(canvas)));
        stage.show();
        tl.play();

    }

    private void run(GraphicsContext gc) {
        //set background color
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width, height);

        //set text color
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font(25));

        if (gameStarted) {
            //set ball movement
            ballXPos += ballXSpeed;
            ballYPos += ballYSpeed;

            //computer
            if (ballXPos < width - width / 4) {
                playerTwoYPos = ballYPos - PLAYER_HEIGHT / 2;
            } else {
                playerTwoYPos = ballYPos > playerTwoYPos + PLAYER_HEIGHT / 2 ? playerTwoYPos += 1 : playerTwoYPos - 1;
            }

            //draw ball
            gc.fillOval(ballXPos, ballYPos, BALL_R, BALL_R);

        } else {
            //set the start text
            gc.setStroke(Color.WHITE);
            gc.setTextAlign(TextAlignment.CENTER);
            gc.strokeText("On Click", width / 2, height / 2);

            // reset the ball start position
            ballXPos = width / 2;
            ballYPos = height / 2;

            //reset speed and direction
            ballXSpeed = new Random().nextInt(2) == 0 ? 1 : -1;
            ballYSpeed = new Random().nextInt(2) == 0 ? 1 : -1;
        }

        //ball stay in canvas
        if (ballYPos > height || ballYPos < 0) {
            ballYSpeed *= -1;
        }

        //computer gets a point
        if (ballXPos < playerOneXPos - PLAYER_WIDHT) {
            scopeP2++;
            gameStarted = false;
        }

        //player get a point
        if (ballXPos > playerTwoXPos + PLAYER_WIDHT) {
            scopeP1++;
            gameStarted = false;
        }

        //increase the ball speed
        if (((ballXPos + BALL_R > playerTwoXPos) && ballYPos >= playerTwoYPos && ballYPos <= playerTwoYPos + PLAYER_HEIGHT)
                || ((ballXPos < playerOneXPos + PLAYER_WIDHT) && ballYPos >= playerOneYPos && ballYPos <= playerOneYPos + PLAYER_HEIGHT)) {
            ballYSpeed += 1 * Math.signum(ballYSpeed);
            ballXSpeed += 1 * Math.signum(ballXSpeed);
            ballXSpeed *= -1;
            //ballYSpeed *= -1;
        }

        //draw scope
        gc.fillText(scopeP1 + "\t\t\t\t\t\t\t\t" + scopeP2, width / 2, 100);

        //draw player 1 and 2
        gc.fillRect(playerTwoXPos, playerTwoYPos, PLAYER_WIDHT, PLAYER_HEIGHT);
        gc.fillRect(playerOneXPos, playerOneYPos, PLAYER_WIDHT, PLAYER_HEIGHT);
    }
}