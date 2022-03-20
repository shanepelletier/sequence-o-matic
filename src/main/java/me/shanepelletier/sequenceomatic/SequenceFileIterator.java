package me.shanepelletier.sequenceomatic;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class SequenceFileIterator implements CustomIterator {
    Scanner inputScanner;

    public SequenceFileIterator(File file) throws IOException {
        inputScanner = new Scanner(file);

    }

    @Override
    public String getNext() {
        return inputScanner.next();
    }

    @Override
    public Boolean hasMore() {
        return inputScanner.hasNext();
    }
}
