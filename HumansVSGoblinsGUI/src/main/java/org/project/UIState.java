package org.project;

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
    public UIState() {
    }

    public UIState(GridPane gp, ProgressBar pb, TextArea ta, Label hp, Label pos) {
        this.gridPane = gp;
        this.progressBar = pb;
        this.textArea = ta;
        this.hp = hp;
        this.pos = pos;
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
}
