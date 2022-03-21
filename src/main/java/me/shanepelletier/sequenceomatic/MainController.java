package me.shanepelletier.sequenceomatic;

import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Stack;

public class MainController {
    public GUIFactory factory;
    public static PianoRoll pianoRoll;
    public BorderPane borderPane;
    public static Sequence sequence;
    final SequenceFileReader fileReader;

    public void initialize() {
        this.borderPane.setCenter(pianoRoll);
    }

    public MainController() {
        factory = new DarkFactory();
        sequence = new Sequence();
        pianoRoll = factory.createPianoRoll(sequence, new Stack<>());
        sequence.subscribe(pianoRoll);
        fileReader = new SequenceFileReader();

    }

    public void newButtonClick() {
        sequence.clear();
    }

    public void saveButtonClick() throws IOException {
        FileChooser fileChooser = new FileChooser();
        File saveFile = fileChooser.showSaveDialog(MainApplication.primaryStage);
        FileWriter writer = new FileWriter(saveFile);
        for (String note : sequence.getNotes()) {
            writer.write(Objects.requireNonNullElse(note, "null"));
            writer.write(" ");
        }
        writer.close();
    }

    public void loadButtonClick() throws IOException {
        FileChooser fileChooser = new FileChooser();
        CustomIterator iterator = fileReader.createIterator(fileChooser.showOpenDialog(MainApplication.primaryStage));
        int i = 0;
        while (iterator.hasMore()) {
            String note = iterator.getNext();
            System.out.println(note);
            sequence.setNote(i, note);
            i++;
        }
    }

    public void playButtonClick() {
        SequencePlayer player = new SequencePlayer(pianoRoll.getSequence());
        player.play();
    }

    public void darkButtonClick() {
        factory = new DarkFactory();
        Sequence oldSequence = pianoRoll.getSequence();
        Stack<Sequence.SequenceMemento> history = pianoRoll.getHistory();

        pianoRoll = factory.createPianoRoll(oldSequence, history);
        sequence.subscribe(pianoRoll);

        update();
    }

    public void lightButtonClick() {
        factory = new LightFactory();
        Sequence oldSequence = pianoRoll.getSequence();
        Stack<Sequence.SequenceMemento> history = pianoRoll.getHistory();

        pianoRoll = factory.createPianoRoll(oldSequence, history);
        sequence.subscribe(pianoRoll);

        update();
    }

    public void update() {
        borderPane.setCenter(pianoRoll);
    }

    public void undoButtonClick() {
        pianoRoll.undo();
    }

}