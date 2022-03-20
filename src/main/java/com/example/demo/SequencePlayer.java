package com.example.demo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Objects;

public class SequencePlayer {
    private final Sequence sequence;
    private Timeline playbackTimeline;
    private final HashMap<String, AudioClip> noteSounds;

    public SequencePlayer(Sequence sequence) {
        this.sequence = sequence.clone();

        noteSounds = new HashMap<>();
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
        noteSounds.put("A4", new AudioClip(Objects.requireNonNull(MainApplication.class.getResource("a.wav")).toString()));

        playbackTimeline = new Timeline();
    }

    public void play() {
        playbackTimeline = new Timeline();

        /*
         * Playing the first audio clip within about 100ms of timeline start results in no sound, so either play the
         * first clip immediately or add a delay to the start of the timeline.
         */
        AudioClip first = noteSounds.get(sequence.getNote(0));
        first.play();

        for (int i = 1; i < 24; ++i) {
            AudioClip noteToPlay = noteSounds.get(sequence.getNote(i));
            if (noteToPlay == null) continue;
            KeyFrame frame = new KeyFrame(Duration.millis(i * 600), actionEvent -> noteToPlay.play());
            playbackTimeline.getKeyFrames().add(frame);
        }

        playbackTimeline.play();
    }
}
