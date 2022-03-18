package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;

public class MainController {
    public GUIFactory factory;
    public static PianoRoll pianoRoll;
    public BorderPane borderPane;
    public static Sequence sequence;
    public Stack<SequenceSnapshot> sequenceSnapshots;
    SequenceFileReader fileReader;

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
        fileReader = new SequenceFileReader();
    }

    public void newButtonClick(ActionEvent actionEvent) {
        Media sound = new Media(Objects.requireNonNull(MainApplication.class.getResource("a.wav")).toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    public void saveButtonClick(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        File saveFile = fileChooser.showSaveDialog(MainApplication.primaryStage);
        FileWriter writer = new FileWriter(saveFile);
        for (String note : sequence.notes) {
            if (note == null) {
                writer.write("null");
            } else {
                writer.write(note);
            }
            writer.write(" ");
        }
        writer.close();
    }

    public void loadButtonClick(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        SequenceFileIterator fileIterator = (SequenceFileIterator) fileReader.createIterator(fileChooser.showOpenDialog(MainApplication.primaryStage));
        int i = 0;
        while (fileIterator.hasMore()) {
            String note = fileIterator.getNext();
            System.out.println(note);
            sequence.setNote(i, note);
            i++;
        }
        pianoRoll.setSequence(sequence);
        pianoRoll.draw();
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