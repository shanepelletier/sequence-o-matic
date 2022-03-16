package com.example.demo;

import javafx.scene.layout.Pane;

public abstract class PianoKey extends Pane {
    protected abstract void draw();
    protected abstract void layoutChildren();
}
