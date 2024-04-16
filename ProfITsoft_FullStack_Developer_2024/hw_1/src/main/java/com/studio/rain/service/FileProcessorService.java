package com.studio.rain.service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class FileProcessorService implements Runnable {
    private final File file;
    private final String attributeName;
    private final Map<String, Integer> statistics;

    public FileProcessorService(File file, String attributeName, Map<String, Integer> statistics) {
        this.file = file;
        this.attributeName = attributeName;
        this.statistics = statistics;
    }

    @Override
    public void run() {
        JsonFactory jsonFactory = new JsonFactory();
        try (JsonParser jsonParser = jsonFactory.createParser(file)) {
            while (jsonParser.nextToken() != null) {
                if (jsonParser.getCurrentToken() == JsonToken.START_OBJECT) {
                    while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                        String fieldName = jsonParser.getCurrentName();
                        if (attributeName.equals(fieldName)) {
                            jsonParser.nextToken();
                            String attributeValue = jsonParser.getText();
                            String[] values = attributeValue.split(",");
                            for (String value : values) {
                                updateStatistics(value.trim());
                            }
                        } else {
                            jsonParser.nextToken();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateStatistics(String value) {
        synchronized (statistics) {
            statistics.put(value, statistics.getOrDefault(value, 0) + 1);
        }
    }
}