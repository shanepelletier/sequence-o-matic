package me.shanepelletier.sequenceomatic;

public class DarkFactory implements GUIFactory{
    @Override
    public PianoRoll createPianoRoll(Sequence sequence) {
        return new PianoRollDark(sequence);
    }
}
