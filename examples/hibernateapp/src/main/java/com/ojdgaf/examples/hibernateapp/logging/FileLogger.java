package com.ojdgaf.examples.hibernateapp.logging;

import java.io.Writer;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements Logger {
    private String fileName;

    public FileLogger(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void log(Object o) throws IOException {
        Writer writer = new FileWriter(fileName);
        writer.write(o.toString() + "\n");
        writer.flush();
        writer.close();
    }
}
