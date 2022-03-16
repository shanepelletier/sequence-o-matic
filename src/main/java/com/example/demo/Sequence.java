package com.example.demo;

public class Sequence {
    String[] notes;

    public Sequence() {
        notes = new String[40];
    }

    public SequenceSnapshot createSnapshot() {
        return new SequenceSnapshot(this, notes);
    }

    public void setNotes(String[] notes) {
        this.notes = notes;
    }

    public void setNote(int position, String note) {
        notes[position] = note;
    }
}
