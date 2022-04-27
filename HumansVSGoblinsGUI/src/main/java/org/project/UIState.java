package org.project;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class UIState {

    private GridPane gridPane;
    private ProgressBar progressBar;
    private TextArea textArea;
    private Label hp;
    private Label pos;
    private Group gameOverGroup;
    private Label gameOverLabel;
    private Label youWinLabel;
    private Group mainGroup;
    public UIState() {
    }

    public UIState(GridPane gp, ProgressBar pb, TextArea ta, Label hp, Label pos, Group gog, Label gl, Label wl, Group mg) {
        this.gridPane = gp;
        this.progressBar = pb;
        this.textArea = ta;
        this.hp = hp;
        this.pos = pos;
        this.gameOverGroup = gog;
        this.gameOverLabel = gl;
        this.youWinLabel = wl;
        this.mainGroup = mg;
    }
    public GridPane getGridPane() {
        return gridPane;
    }

    public void setGridPane(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public TextArea getTextArea() {
        return textArea;
    }

    public void setTextArea(TextArea textArea) {
        this.textArea = textArea;
    }

    public Label getHp() {
        return hp;
    }

    public void setHp(Label hp) {
        this.hp = hp;
    }

    public Label getPos() {
        return pos;
    }

    public void setPos(Label pos) {
        this.pos = pos;
    }

    public Group getGameOverGroup() {
        return gameOverGroup;
    }

    public void setGameOverGroup(Group gameOverGroup) {
        this.gameOverGroup = gameOverGroup;
    }

    public Label getGameOverLabel() {
        return gameOverLabel;
    }

    public void setGameOverLabel(Label gameOverLabel) {
        this.gameOverLabel = gameOverLabel;
    }

    public Label getYouWinLabel() {
        return youWinLabel;
    }

    public void setYouWinLabel(Label youWinLabel) {
        this.youWinLabel = youWinLabel;
    }

    public Group getMainGroup() {
        return mainGroup;
    }

    public void setMainGroup(Group mainGroup) {
        this.mainGroup = mainGroup;
    }
}
