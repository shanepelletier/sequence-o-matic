package me.shanepelletier.sequenceomatic;

import java.util.Stack;

public interface GUIFactory {
    PianoRoll createPianoRoll(Sequence sequence, Stack<Sequence.SequenceMemento> history);
}
