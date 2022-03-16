package com.example.demo;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PianoRollDark extends PianoRoll {
    private final Canvas canvas;

    public PianoRollDark() {
        canvas = new Canvas();
        this.getChildren().add(canvas);
        Rectangle note = new Rectangle(50, 50, 50, 50);
        note.setFill(Color.GREEN);
        this.getChildren().add(note);
    }

    protected void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.DARKGRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    @Override
    protected void layoutChildren() {
        canvas.setWidth(this.getWidth());
        canvas.setHeight(this.getHeight());

        draw();
    }
}
