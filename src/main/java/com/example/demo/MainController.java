package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.Objects;

public class MainController {
    public GUIFactory factory;
    public PianoRoll pianoRoll;
    public BorderPane borderPane;
    public Sequence sequence;

    public void initialize() {
        System.out.println("Initialized");
        this.borderPane.setCenter(pianoRoll);
    }

    public MainController() {
        factory = new DarkFactory();
        pianoRoll = factory.createPianoRoll();
        sequence = new Sequence();
        sequence.setNote(0, "A");
        sequence.setNote(2, "C");
        sequence.setNote(4, "E");
        sequence.setNote(5, "E");
    }

    public void newButtonClick(ActionEvent actionEvent) {
        Media sound = new Media(Objects.requireNonNull(MainApplication.class.getResource("a.wav")).toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    public void saveButtonClick(ActionEvent actionEvent) {
        Media sound = new Media(Objects.requireNonNull(MainApplication.class.getResource("c.wav")).toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    public void loadButtonClick(ActionEvent actionEvent) {
        Media sound = new Media(Objects.requireNonNull(MainApplication.class.getResource("e.wav")).toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
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