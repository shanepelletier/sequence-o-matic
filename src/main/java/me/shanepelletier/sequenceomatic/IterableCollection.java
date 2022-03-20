package me.shanepelletier.sequenceomatic;

import java.io.File;
import java.io.IOException;

public interface IterableCollection {
    CustomIterator createIterator(File file) throws IOException;
}
