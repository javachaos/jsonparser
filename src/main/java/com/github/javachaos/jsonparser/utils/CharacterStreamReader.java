package com.github.javachaos.jsonparser.utils;

import com.github.javachaos.jsonparser.exceptions.JsonParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CharacterStreamReader implements Iterator<Character>, AutoCloseable {

    private static final Logger LOGGER = LogManager.getLogger(CharacterStreamReader.class);

    private final Reader reader;

    private int nextChar;

    public CharacterStreamReader(InputStream is) {
        reader = new InputStreamReader(is);
        try {
            nextChar = reader.read();
        } catch (IOException e) {
            LOGGER.error("Error reading first character: {}", e.getMessage());
        }
    }

    @Override
    public boolean hasNext() {
        return nextChar != -1;
    }

    @Override
    public Character next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (nextChar < 0 || nextChar > 65535) {
            throw new JsonParseException("Input character out of range.");
        }
        char ch = (char) nextChar;
        try {
            nextChar = reader.read();
        } catch (IOException e) {
            throw new JsonParseException("Error reading from input stream.");
        }
        return ch;
    }

    public Character peek() {
        return (char) nextChar;
    }

    @Override
    public void close() throws Exception {
        reader.close();
    }

    public void mark(int i) {
        if (reader.markSupported()) {
            try {
                reader.mark(i);
            } catch (IOException e) {
                throw new JsonParseException(e);
            }
        }
    }

    public void reset() {
        if (reader.markSupported()) {
            try {
                reader.reset();
            } catch (IOException e) {
                throw new JsonParseException(e);
            }
        }
    }
}
