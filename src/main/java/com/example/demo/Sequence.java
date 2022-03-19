package com.example.demo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class Sequence {
    String[] notes;
    private final HashMap<String, AudioClip> noteSounds;
    Timeline playbackTimeline;
    ArrayList<SequenceSubscriber> subscribers;

    public Sequence() {
        notes = new String[24];
        noteSounds = new HashMap<>();
        subscribers = new ArrayList<>();
        AudioClip temp = new AudioClip(Objects.requireNonNull(MainApplication.class.getResource("a.wav")).toString());
        noteSounds.put("A5", new AudioClip(Objects.requireNonNull(MainApplication.class.getResource("aoctave.wav")).toString()));
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

    public void clear() {
        Arrays.fill(notes, null);
        notifySubscribers();
    }

    public void setNotes(String[] notes) {
        this.notes = notes;
        notifySubscribers();
    }

    public String getNote(int position) {
        return notes[position];
    }

    public void setNote(int position, String note) {
        notes[position] = note;
        notifySubscribers();
    }

    public void play() {
        playbackTimeline = new Timeline();
        for (int i = 0; i < 24; ++i) {
            // 100 bpm = 1 beat every 0.6s
            AudioClip noteToPlay = noteSounds.get(notes[i]);
            if (noteToPlay == null) continue;
            KeyFrame frame = new KeyFrame(Duration.millis(i * 600), actionEvent -> noteToPlay.play());
            playbackTimeline.getKeyFrames().add(frame);
        }

        playbackTimeline.play();
    }

    public Sequence clone() {
        Sequence newSequence = new Sequence();
        newSequence.setNotes(this.notes.clone());
        return newSequence;
    }

    public void subscribe(SequenceSubscriber sequenceSubscriber) {
        subscribers.add(sequenceSubscriber);
    }

    public void unsubscribe(SequenceSnapshot sequenceSubscriber) {
        subscribers.remove(sequenceSubscriber);
    }

    public void notifySubscribers() {
        for (SequenceSubscriber sequenceSubscriber : subscribers) {
            sequenceSubscriber.sequenceUpdate(this);
        }
    }
}
