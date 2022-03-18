package com.example.demo;

import java.io.IOException;

public interface IterableCollection {
    CustomIterator createIterator(String filename) throws IOException;
}
