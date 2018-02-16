package com.thales.googlehashcode.hash2018;

import lombok.extern.log4j.Log4j2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Log4j2
public class OutputWriter {
    private final String outputFilename;
    private final File outputFolder;

    public OutputWriter(final String name, File outputFolder) {
        this.outputFilename = name.replace(".in", ".out");
        this.outputFolder = outputFolder;
    }

    public void write(final List<String> lines) throws IOException {

        final File outputFile = new File(outputFolder, outputFilename);
        final FileWriter fileWriter = new FileWriter(outputFile);
        final BufferedWriter writer = new BufferedWriter(fileWriter);

        for (String line: lines) {
            writer.write(line);
            writer.newLine();
        }
        writer.close();
        fileWriter.close();
    }
}
