package com.example.demo;

import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;

public class PianoRollLight extends PianoRoll {
    private final Canvas canvas;

    private final String[] KEY_NAMES = {"A", "G", "F#", "F", "E", "D#", "D", "C#", "C", "B", "A#", "A"};
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

        gc.setStroke(Color.BLACK);
        gc.setTextBaseline(VPos.CENTER);
        gc.setTextAlign(TextAlignment.CENTER);
        final double RECTANGLE_HEIGHT = canvas.getHeight() / 12;
        for (int i = 0; i < 12; ++i) {
            gc.setFill(Color.WHITE);
            keys[i] = new Rectangle(0, i * RECTANGLE_HEIGHT, 40, RECTANGLE_HEIGHT);
            gc.fillRect(0, i * RECTANGLE_HEIGHT, 40, i * RECTANGLE_HEIGHT + RECTANGLE_HEIGHT);
            gc.strokeRect(0, i * RECTANGLE_HEIGHT, 40, i * RECTANGLE_HEIGHT + RECTANGLE_HEIGHT);
            gc.setFill(Color.BLACK);
            gc.fillText(KEY_NAMES[i], 20, i * RECTANGLE_HEIGHT + RECTANGLE_HEIGHT / 2);
        }
    }

    @Override
    protected void layoutChildren() {
        canvas.setWidth(this.getWidth());
        canvas.setHeight(this.getHeight());

        draw();
    }

}
