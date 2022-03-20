package me.shanepelletier.sequenceomatic;

public class SequenceSnapshot {
    final PianoRoll pianoRoll;
    final Sequence sequence;

    public SequenceSnapshot(PianoRoll pianoRoll, Sequence sequence) {
        this.pianoRoll = pianoRoll;
        this.sequence = sequence;
    }

    public void restore() {
        MainController.setSequence(sequence);
    }
}
