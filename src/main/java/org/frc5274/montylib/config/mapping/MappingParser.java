package org.frc5274.montylib.config.mapping;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MappingParser {
    public JSONObject map;

    public MappingParser(String filepath) throws FileNotFoundException, IOException, ParseException {
        JSONParser parser = new JSONParser();
        Reader fileReader = new FileReader(filepath);
        map = (JSONObject) parser.parse(fileReader);
    }
}
