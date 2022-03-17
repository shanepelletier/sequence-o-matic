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
import java.util.Set;
import java.util.Stack;

public class MainController {
    public GUIFactory factory;
    public static PianoRoll pianoRoll;
    public BorderPane borderPane;
    public static Sequence sequence;
    public Stack<SequenceSnapshot> sequenceSnapshots;

    public void initialize() {
        System.out.println("Initialized");
        this.borderPane.setCenter(pianoRoll);
    }

    public MainController() {
        factory = new DarkFactory();
        pianoRoll = factory.createPianoRoll();
        sequence = new Sequence();
        pianoRoll.setSequence(sequence);
        sequenceSnapshots = new Stack<>();
        pianoRoll.setSequenceSnapshots(sequenceSnapshots);
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
        pianoRoll.getSequence().play();
    }

    public void darkButtonClick(ActionEvent actionEvent) {
        System.out.println("Switching to dark mode");
        factory = new DarkFactory();
        Sequence oldSequence = pianoRoll.getSequence();
        System.out.println("Sequence before switch: ");
        for (String note : sequence.notes) {
            System.out.print(note + " ");
        }
        System.out.println();
        Stack<SequenceSnapshot> sequenceSnapshots = pianoRoll.getSequenceSnapshots();
        pianoRoll = factory.createPianoRoll();
        pianoRoll.setSequence(oldSequence);
        pianoRoll.setSequenceSnapshots(sequenceSnapshots);
        System.out.println("Sequence after switch: ");
        for (String note : sequence.notes) {
            System.out.print(note + " ");
        }
        System.out.println();
        pianoRoll.draw();
        update();
    }

    public void lightButtonClick(ActionEvent actionEvent) {
        factory = new LightFactory();
        Sequence oldSequence = pianoRoll.getSequence();
        Stack<SequenceSnapshot> sequenceSnapshots = pianoRoll.getSequenceSnapshots();
        pianoRoll = factory.createPianoRoll();
        pianoRoll.setSequence(oldSequence);
        pianoRoll.setSequenceSnapshots(sequenceSnapshots);
        pianoRoll.draw();
        update();
    }

    public static void setSequence(Sequence newSequence) {
        sequence = newSequence;
        pianoRoll.setSequence(sequence);
    }

    public void update() {
        borderPane.setCenter(pianoRoll);
    }

    public void undoButtonClick(ActionEvent actionEvent) {
        pianoRoll.undo();
    }
}