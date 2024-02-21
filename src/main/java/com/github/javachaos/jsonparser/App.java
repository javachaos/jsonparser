package com.github.javachaos.jsonparser;

import com.github.javachaos.jsonparser.parser.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * JsonParse Demo.
 *
 */
public class App 
{
    private static final Logger LOGGER = LogManager.getLogger(App.class);
    public static void main( String[] args )
    {
        JsonParser jp = new JsonParser("/test.json");
        String file = jp.printFile();
        LOGGER.debug(file);
        LOGGER.debug(jp.parse());
    }
}
