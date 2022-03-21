package me.shanepelletier.sequenceomatic;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;

import java.util.Stack;

public class PianoRollDark extends PianoRoll {

    public PianoRollDark(Sequence sequence, Stack<Sequence.SequenceMemento> history) {
        super(sequence, history);
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
}
