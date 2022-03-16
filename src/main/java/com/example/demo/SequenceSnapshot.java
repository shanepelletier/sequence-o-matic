package com.example.demo;

public class SequenceSnapshot {
    Sequence sequence;
    String[] notes;

    public SequenceSnapshot(Sequence sequence, String[] notes) {
        this.sequence = sequence;
        this.notes = notes;
    }

    public void restore() {
        sequence.setNotes(notes);
    }
}
