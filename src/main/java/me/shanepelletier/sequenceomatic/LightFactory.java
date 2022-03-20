package me.shanepelletier.sequenceomatic;

public class LightFactory implements GUIFactory {

    @Override
    public PianoRoll createPianoRoll(Sequence sequence) {
        return new PianoRollLight(sequence);
    }
}
