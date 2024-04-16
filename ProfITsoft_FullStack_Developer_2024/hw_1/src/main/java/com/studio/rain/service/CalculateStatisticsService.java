
package com.studio.rain.service;

import java.io.File;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * Service class to calculate statistics from JSON files in a specified folder.
 */
public class CalculateStatisticsService {

    /**
     * Calculates statistics based on JSON files in the specified folder.
     *
     * @param folderPath     The path to the folder containing the JSON files.
     * @param attributeName  The attribute name to be used for calculating statistics.
     * @param numThreads     The number of threads to process files concurrently.
     * @return A map containing the calculated statistics.
     * @throws IllegalArgumentException if the specified path is incorrect.
     */
    public Map<String, Integer> calculateStatistics(String folderPath, String attributeName, int numThreads) {
        try {
            Map<String, Integer> statistics = new TreeMap<>(Comparator.naturalOrder());
            File folder = new File(folderPath);
            ExecutorService executor = Executors.newFixedThreadPool(numThreads);
            if (folder.exists()) {
                if (folder.isDirectory()) {
                    File[] files = folder.listFiles();
                    for (File file : files) {
                        if (file.isFile() && file.getName().toLowerCase().endsWith(".json")) {
                            executor.execute(new FileProcessorService(file, attributeName, statistics));
                        }
                    }
                } else {
                    if (folder.isFile() && folder.getName().toLowerCase().endsWith(".json")) {
                        executor.execute(new FileProcessorService(folder, attributeName, statistics));
                    }
                }
                executor.shutdown();
                executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);

                return statistics;
            } else {
                throw new IllegalArgumentException("Path is incorrect");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
