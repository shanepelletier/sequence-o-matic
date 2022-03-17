package com.example.demo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Objects;

public class Sequence {
    String[] notes;
    private HashMap<String, AudioClip> noteSounds;
    Timeline playbackTimeline;

    public Sequence() {
        notes = new String[24];
        noteSounds = new HashMap<>();
        AudioClip temp = new AudioClip(Objects.requireNonNull(MainApplication.class.getResource("a.wav")).toString());
        noteSounds.put("A#5", new AudioClip(Objects.requireNonNull(MainApplication.class.getResource("aoctave.wav")).toString()));
        noteSounds.put("G4", new AudioClip(Objects.requireNonNull(MainApplication.class.getResource("g.wav")).toString()));
        noteSounds.put("F#4", new AudioClip(Objects.requireNonNull(MainApplication.class.getResource("fsharp.wav")).toString()));
        noteSounds.put("F4", new AudioClip(Objects.requireNonNull(MainApplication.class.getResource("f.wav")).toString()));
        noteSounds.put("E4", new AudioClip(Objects.requireNonNull(MainApplication.class.getResource("e.wav")).toString()));
        noteSounds.put("D#4", new AudioClip(Objects.requireNonNull(MainApplication.class.getResource("dsharp.wav")).toString()));
        noteSounds.put("D4", new AudioClip(Objects.requireNonNull(MainApplication.class.getResource("d.wav")).toString()));
        noteSounds.put("C#4",new AudioClip(Objects.requireNonNull(MainApplication.class.getResource("csharp.wav")).toString()));
        noteSounds.put("C4", new AudioClip(Objects.requireNonNull(MainApplication.class.getResource("c.wav")).toString()));
        noteSounds.put("B4", new AudioClip(Objects.requireNonNull(MainApplication.class.getResource("b.wav")).toString()));
        noteSounds.put("A#4", new AudioClip(Objects.requireNonNull(MainApplication.class.getResource("asharp.wav")).toString()));
        noteSounds.put("A4", temp);

        playbackTimeline = new Timeline();
    }

    public void setNotes(String[] notes) {
        this.notes = notes;
    }

    public String getNote(int position) {
        return notes[position];
    }

    public void setNote(int position, String note) {
        notes[position] = note;
    }

    public void play() {
        playbackTimeline.stop();
        for (int i = 0; i < 24; ++i) {
            // 100 bpm = 1 beat every 0.6s
            int finalI = i;
            KeyFrame frame = new KeyFrame(Duration.millis(i * 600), actionEvent -> {
                noteSounds.get(notes[finalI]).play();
            });
            playbackTimeline.getKeyFrames().add(frame);
        }
//        KeyFrame endFrame = new KeyFrame(Duration.millis(24000), actionEvent -> {
//            playbackTimeline.stop();
//        });
//        playbackTimeline.getKeyFrames().add(endFrame);
        playbackTimeline.play();
    }

    public Sequence clone() {
        Sequence newSequence = new Sequence();
        newSequence.setNotes(this.notes.clone());
        return newSequence;
    }
}
