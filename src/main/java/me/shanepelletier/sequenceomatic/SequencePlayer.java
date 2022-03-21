package me.shanepelletier.sequenceomatic;

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
        this.sequence = sequence;

        noteSounds = new HashMap<>();
        noteSounds.put("A5", new AudioClip(Objects.requireNonNull(MainApplication.class.getResource("aoctave.wav")).toString()));
        noteSounds.put("G5", new AudioClip(Objects.requireNonNull(MainApplication.class.getResource("g.wav")).toString()));
        noteSounds.put("F#5", new AudioClip(Objects.requireNonNull(MainApplication.class.getResource("fsharp.wav")).toString()));
        noteSounds.put("F5", new AudioClip(Objects.requireNonNull(MainApplication.class.getResource("f.wav")).toString()));
        noteSounds.put("E5", new AudioClip(Objects.requireNonNull(MainApplication.class.getResource("e.wav")).toString()));
        noteSounds.put("D#5", new AudioClip(Objects.requireNonNull(MainApplication.class.getResource("dsharp.wav")).toString()));
        noteSounds.put("D5", new AudioClip(Objects.requireNonNull(MainApplication.class.getResource("d.wav")).toString()));
        noteSounds.put("C#5",new AudioClip(Objects.requireNonNull(MainApplication.class.getResource("csharp.wav")).toString()));
        noteSounds.put("C5", new AudioClip(Objects.requireNonNull(MainApplication.class.getResource("c.wav")).toString()));
        noteSounds.put("B4", new AudioClip(Objects.requireNonNull(MainApplication.class.getResource("b.wav")).toString()));
        noteSounds.put("A#4", new AudioClip(Objects.requireNonNull(MainApplication.class.getResource("asharp.wav")).toString()));
        noteSounds.put("A4", new AudioClip(Objects.requireNonNull(MainApplication.class.getResource("a.wav")).toString()));

        playbackTimeline = new Timeline();
    }

    public void play() {
        playbackTimeline = new Timeline();

        for (int i = 0; i < 24; ++i) {
            AudioClip noteToPlay = noteSounds.get(sequence.getNote(i));
            if (noteToPlay == null) continue;
            KeyFrame frame = new KeyFrame(Duration.millis(i * 600 + 100), actionEvent -> noteToPlay.play());
            playbackTimeline.getKeyFrames().add(frame);
        }

        playbackTimeline.play();
    }
}
