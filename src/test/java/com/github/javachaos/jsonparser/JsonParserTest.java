package com.github.javachaos.jsonparser;

import com.github.javachaos.jsonparser.parser.JsonParser;
import com.github.javachaos.jsonparser.utils.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonParserTest {

    private static final Logger LOGGER = LogManager.getLogger(JsonParserTest.class);
    @Test
    void testJsonParser() throws Exception {
        JsonParser jp = new JsonParser("/test.json");
        LOGGER.debug(jp.printFile());
        Set<Pair<String, Object>> data = jp.parse();
        for(Pair<String, Object> p : data) {
            LOGGER.debug(p + "\n");
            assertEquals("[menu : [[id : [file], " +
                    "value : [other], popup : [[test : [12e18], " +
                    "menuitem : [[[[value : [New], onclick : [CreateNewDoc()]]], [[" +
                    "value : [Open], onclick : [OpenDoc()]]], [[" +
                    "onclick : [CloseDoc()\\u8789], value : [Close]]], [null], [-2872], " +
                    "[-923.1231], [102], []]]]], value : [File]]]]", p.toString());
        }
    }

}
