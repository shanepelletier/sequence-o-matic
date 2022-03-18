package com.example.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class SequenceFileIterator implements CustomIterator {
    Scanner inputScanner;

    public SequenceFileIterator(File file) throws IOException {
        inputScanner = new Scanner(file);

    }

    @Override
    public String getNext() {
        String temp = inputScanner.next();
        System.out.println("GOT " + temp);
        return temp;
    }

    @Override
    public Boolean hasMore() {
        return inputScanner.hasNext();
    }
}
