package com.studio.rain.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studio.rain.entity.EntryStatistic;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateDefaultJSON {
    public void generateStartJSON(File file) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Random random = new Random();

        int randomNumber = random.nextInt(100,5000);
        System.out.println("Random Number of entries = " + randomNumber);
        List<EntryStatistic> entryStatistics = generateRandomEntryStatistics(randomNumber);
        objectMapper.writeValue(file, entryStatistics);


    }

    private static final int MAX_NAME_LENGTH = 10;
    private static final int MIN_BIRTH_YEAR = 1900;
    private static final int MAX_BIRTH_YEAR = 2022;
    private static final int MAX_GROUP_LENGTH = 20;

    public List<EntryStatistic> generateRandomEntryStatistics(int count) {
        List<EntryStatistic> entryStatistics = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            String firstName = generateRandomString(random, MAX_NAME_LENGTH);
            String lastName = generateRandomString(random, MAX_NAME_LENGTH);
            int birthYear = random.nextInt(MAX_BIRTH_YEAR - MIN_BIRTH_YEAR + 1) + MIN_BIRTH_YEAR;
            String group = generateRandomString(random, MAX_GROUP_LENGTH);
            entryStatistics.add(new EntryStatistic(firstName, lastName, birthYear, group));
        }

        return entryStatistics;
    }

    private String generateRandomString(Random random, int maxLength) {
        StringBuilder sb = new StringBuilder();
        int length = random.nextInt(maxLength) + 1;
        for (int i = 0; i < length; i++) {
            char c = (char) (random.nextInt(26) + 'a');
            sb.append(c);
        }
        return sb.toString();
    }

}
