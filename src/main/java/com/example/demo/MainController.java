package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;

public class MainController {
    public GUIFactory factory;
    public PianoRoll pianoRoll;
    public BorderPane borderPane;

    public void initialize() {
        System.out.println("Initialized");
        this.borderPane.setCenter(pianoRoll);
    }

    public MainController() {
        factory = new DarkFactory();
        pianoRoll = factory.createPianoRoll();

    }

    public void newButtonClick(ActionEvent actionEvent) {
    }

    public void saveButtonClick(ActionEvent actionEvent) {
    }

    public void loadButtonClick(ActionEvent actionEvent) {
    }

    public void exportButtonClick(ActionEvent actionEvent) {
    }

    public void playButtonClick(ActionEvent actionEvent) {
    }

    public void darkButtonClick(ActionEvent actionEvent) {
        factory = new DarkFactory();
        pianoRoll = factory.createPianoRoll();
        update();
    }

    public void lightButtonClick(ActionEvent actionEvent) {
        factory = new LightFactory();
        pianoRoll = factory.createPianoRoll();
        update();
    }

    public void update() {
        borderPane.setCenter(pianoRoll);
    }
}