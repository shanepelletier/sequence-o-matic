package me.shanepelletier.sequenceomatic;

import java.util.Stack;

public class DarkFactory implements GUIFactory{

    @Override
    public PianoRoll createPianoRoll(Sequence sequence, Stack<Sequence.SequenceMemento> history) {
        return new PianoRollDark(sequence, history);
    }
}
