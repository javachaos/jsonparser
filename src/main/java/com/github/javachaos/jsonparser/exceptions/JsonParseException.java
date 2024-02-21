package com.github.javachaos.jsonparser.exceptions;

import java.io.IOException;

public class JsonParseException extends RuntimeException {
    public JsonParseException(String s) {
        super(s);
    }
    public JsonParseException(IOException e) {
        super(e);
    }
}
