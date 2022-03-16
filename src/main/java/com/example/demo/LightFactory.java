package com.example.demo;

public class LightFactory implements GUIFactory {

    @Override
    public PianoRoll createPianoRoll() {
        return new PianoRollLight();
    }
}
