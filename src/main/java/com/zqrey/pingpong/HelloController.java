package com.zqrey.pingpong;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class HelloController {

    @FXML
    private Pane game_field;
    @FXML
    private Rectangle player, bot;
    @FXML
    private Circle bol;

    @FXML
    protected void onKeyPressed(KeyEvent event){
        if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.W){
            player.setLayoutY(player.getLayoutY() - 5);
        }
        if (event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.S){
            player.setLayoutY(player.getLayoutY() + 5);
        }
    }
}