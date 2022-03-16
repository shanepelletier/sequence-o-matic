package com.example.demo;

public class DarkFactory implements GUIFactory{
    @Override
    public PianoRoll createPianoRoll() {
        return new PianoRollDark();
    }
}
