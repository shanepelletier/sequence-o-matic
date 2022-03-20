package me.shanepelletier.sequenceomatic;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.Stack;

public abstract class PianoRoll extends Pane implements SequenceSubscriber {
    protected final Canvas canvas;

    protected final String[] KEY_NAMES = {"A5", "G5", "F#5", "F5", "E5", "D#5", "D5", "C#5", "C5", "B4", "A#4", "A4"};
    protected Sequence sequence;
    protected Stack<SequenceSnapshot> sequenceSnapshots;
    protected final Rectangle[] keys;

    protected PianoRoll(Sequence sequence) {
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

    protected void layoutChildren() {
        canvas.setWidth(this.getWidth());
        canvas.setHeight(this.getHeight());

        draw();
    }

    protected Sequence getSequence() { return sequence; }

    protected void setSequenceSnapshots(Stack<SequenceSnapshot> sequenceSnapshots) {
        this.sequenceSnapshots = sequenceSnapshots;
    }

    protected Stack<SequenceSnapshot> getSequenceSnapshots() {
        return sequenceSnapshots;
    }

    protected SequenceSnapshot createSnapshot(){
        return new SequenceSnapshot(this, sequence.clone());
    }

    protected void undo() {
        sequenceSnapshots.pop().restore();
        draw();
    }

    public void sequenceUpdate(Sequence sequence) {
        this.sequence = sequence;
        draw();
    }

    protected abstract void draw();
}
