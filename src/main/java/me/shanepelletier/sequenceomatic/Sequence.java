package me.shanepelletier.sequenceomatic;

import java.util.ArrayList;
import java.util.Arrays;

public class Sequence {
    String[] notes;
    final ArrayList<SequenceSubscriber> subscribers;

    public Sequence() {
        notes = new String[24];
        subscribers = new ArrayList<>();
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

    public void subscribe(SequenceSubscriber sequenceSubscriber) {
        subscribers.add(sequenceSubscriber);
    }


    public void notifySubscribers() {
        for (SequenceSubscriber sequenceSubscriber : subscribers) {
            sequenceSubscriber.sequenceUpdate(this);
        }
    }

    public SequenceMemento save() {
        return new SequenceMemento(notes.clone());
    }

    public void restore(SequenceMemento memento) {
        notes = memento.getNotes();
        System.out.println();
        notifySubscribers();
    }

    static class SequenceMemento {
        String[] notes;

        SequenceMemento(String[] notes) {
            this.notes = notes;
        }

        String[] getNotes() {
            return notes;
        }
    }
}
