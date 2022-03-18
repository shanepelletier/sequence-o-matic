package com.example.demo;

import java.io.IOException;

public class SequenceFileReader implements IterableCollection {

    @Override
    public CustomIterator createIterator(String filename) throws IOException {
        return new SequenceFileIterator(filename);
    }
}
