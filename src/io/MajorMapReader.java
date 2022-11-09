package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MajorMapReader {

    private final File majorMapFile;

    public MajorMapReader(String fileName) {
        majorMapFile = new File(fileName);
    }


    public Map<String, String> readMajorMap() {
        HashMap<String, String> map = new HashMap<>();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(majorMapFile))) {
            while ((line = reader.readLine()) != null) {
                String[] keyValuePair = line.split("\t");
                if (keyValuePair.length > 1) {
                    String key = keyValuePair[0].trim();
                    String value = keyValuePair[1].trim();
                    map.put(key, value);
                } else {
                    System.out.println("No Key:Value found in line, ignoring: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;

    }
}
