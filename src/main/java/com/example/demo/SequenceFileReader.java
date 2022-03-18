package com.example.demo;

import java.io.File;
import java.io.IOException;

public class SequenceFileReader implements IterableCollection {

    @Override
    public CustomIterator createIterator(File file) throws IOException {
        return new SequenceFileIterator(file);
    }
}
