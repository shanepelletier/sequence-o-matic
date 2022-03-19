package com.example.demo;

import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import java.util.Stack;

public class PianoRollDark extends PianoRoll {
    private final Canvas canvas;

    private final String[] KEY_NAMES = {"A5", "G4", "F#4", "F4", "E4", "D#4", "D4", "C#4", "C4", "B4", "A#4", "A4"};
    Sequence sequence;
    Stack<SequenceSnapshot> sequenceSnapshots;
    Rectangle[] keys;

    public PianoRollDark(Sequence sequence) {
        canvas = new Canvas();
        keys = new Rectangle[12];

        this.getChildren().add(canvas);

        this.sequence = sequence;

        canvas.setOnMouseClicked(e -> {
            final double RECTANGLE_HEIGHT = canvas.getHeight() / 12;
            final double NOTE_WIDTH = (canvas.getWidth() - 40) / 24;

            int noteX = (int)((e.getX() - 40) / NOTE_WIDTH);
            int noteY = (int)(e.getY() / RECTANGLE_HEIGHT);

            sequenceSnapshots.add(this.createSnapshot());
            this.sequence.setNote(noteX, KEY_NAMES[noteY]);
            draw();
        });
    }

    protected void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.DIMGRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.setStroke(Color.WHITE);
        gc.setTextBaseline(VPos.CENTER);
        gc.setTextAlign(TextAlignment.CENTER);
        final double RECTANGLE_HEIGHT = canvas.getHeight() / 12;
        for (int i = 0; i < 12; ++i) {
            gc.setFill(Color.BLACK);
            keys[i] = new Rectangle(0, i * RECTANGLE_HEIGHT, 40, RECTANGLE_HEIGHT);
            gc.fillRect(0, i * RECTANGLE_HEIGHT, 40, i * RECTANGLE_HEIGHT + RECTANGLE_HEIGHT);
            gc.strokeRect(0, i * RECTANGLE_HEIGHT, 40, i * RECTANGLE_HEIGHT + RECTANGLE_HEIGHT);
            gc.setFill(Color.WHITE);
            gc.fillText(KEY_NAMES[i], 20, i * RECTANGLE_HEIGHT + RECTANGLE_HEIGHT / 2);
        }

        final double NOTE_WIDTH = (canvas.getWidth() - 40) / 24;
        for (int i = 0; i < 12; ++i) {
            for (int j = 0; j < 24; ++j) {
                gc.setStroke(Color.WHITE);
                gc.setFill(Color.BLACK);
                String note = sequence.getNote(j);
                if (note != null && note.equals(KEY_NAMES[i])) {
                    gc.setFill(Color.GREEN);
                }
                gc.strokeRect(j * NOTE_WIDTH + 40, i * RECTANGLE_HEIGHT, j * NOTE_WIDTH + NOTE_WIDTH, i * RECTANGLE_HEIGHT + RECTANGLE_HEIGHT);
                gc.fillRect(j * NOTE_WIDTH + 40, i * RECTANGLE_HEIGHT, j * NOTE_WIDTH + NOTE_WIDTH, i * RECTANGLE_HEIGHT + RECTANGLE_HEIGHT);
            }
        }
    }

    @Override
    protected void layoutChildren() {
        canvas.setWidth(this.getWidth());
        canvas.setHeight(this.getHeight());

        draw();
    }

    @Override
    protected Sequence getSequence() {
        return sequence;
    }

    @Override
    protected void setSequence(Sequence sequence) {
        this.sequence = sequence;
    }

    protected void setSequenceSnapshots(Stack<SequenceSnapshot> sequenceSnapshots) {
        this.sequenceSnapshots = sequenceSnapshots;
    }

    @Override
    protected Stack<SequenceSnapshot> getSequenceSnapshots() {
        return sequenceSnapshots;
    }

    protected SequenceSnapshot createSnapshot(){
        return new SequenceSnapshot(this, sequence.clone());
    }

    @Override
    protected void undo() {
        sequenceSnapshots.pop().restore();
        draw();
    }

    @Override
    public void sequenceUpdate(Sequence sequence) {
        this.sequence = sequence;
        draw();
    }
}
