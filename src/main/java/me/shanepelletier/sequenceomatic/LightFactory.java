package me.shanepelletier.sequenceomatic;

import java.util.Stack;

public class LightFactory implements GUIFactory {

    @Override
    public PianoRoll createPianoRoll(Sequence sequence, Stack<Sequence.SequenceMemento> history) {
        return new PianoRollLight(sequence, history);
    }
}
