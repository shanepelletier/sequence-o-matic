package com.example.demo;

public class SequenceSnapshot {
    PianoRoll pianoRoll;
    Sequence sequence;

    public SequenceSnapshot(PianoRoll pianoRoll, Sequence sequence) {
        this.pianoRoll = pianoRoll;
        this.sequence = sequence;
        System.out.println("Creating snapshot with sequence: ");
        for (String note : sequence.notes) {
            System.out.print(note + " ");
        }
        System.out.println();
    }

    public void restore() {
        System.out.println("Restoring snapshot with sequence: ");
        for (String note : sequence.notes) {
            System.out.print(note + " ");
        }
        System.out.println();
        MainController.setSequence(sequence);
    }
}
