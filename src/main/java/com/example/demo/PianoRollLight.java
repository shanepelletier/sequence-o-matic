package com.example.demo;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PianoRollLight extends PianoRoll {
    private final Canvas canvas;

    private final String[] KEY_NAMES = {"A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "A"};
    Rectangle[] keys;

    public PianoRollLight() {
        canvas = new Canvas();
        keys = new Rectangle[12];

        this.getChildren().add(canvas);
        Rectangle note = new Rectangle(50, 50, 50, 50);
        note.setFill(Color.GREEN);
        this.getChildren().add(note);
    }

    protected void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.WHEAT);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.setFill(Color.WHITE);
        gc.setStroke(Color.BLACK);
        final double RECTANGLE_HEIGHT = canvas.getHeight() / 12;
        for (int i = 0; i < 12; ++i) {
            keys[i] = new Rectangle(0, i * RECTANGLE_HEIGHT, 40, RECTANGLE_HEIGHT);
            gc.fillRect(0, i * RECTANGLE_HEIGHT, 40, i * RECTANGLE_HEIGHT + RECTANGLE_HEIGHT);
            gc.strokeRect(0, i * RECTANGLE_HEIGHT, 40, i * RECTANGLE_HEIGHT + RECTANGLE_HEIGHT);

        }
    }

    @Override
    protected void layoutChildren() {
        canvas.setWidth(this.getWidth());
        canvas.setHeight(this.getHeight());

        draw();
    }

}
