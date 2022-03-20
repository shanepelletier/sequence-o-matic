package me.shanepelletier.sequenceomatic;

import javafx.scene.layout.Pane;

import java.util.Stack;

public abstract class PianoRoll extends Pane implements SequenceSubscriber {
    protected abstract void draw();
    protected abstract void layoutChildren();
    protected abstract Sequence getSequence();
    protected abstract void setSequence(Sequence sequence);
    protected abstract void setSequenceSnapshots(Stack<SequenceSnapshot> sequenceSnapshots);
    protected abstract Stack<SequenceSnapshot> getSequenceSnapshots();
    protected abstract SequenceSnapshot createSnapshot() throws CloneNotSupportedException;
    protected abstract void undo();
    public abstract void sequenceUpdate(Sequence sequence);
}
