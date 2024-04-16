
package com.studio.rain.facade;

import com.studio.rain.service.CalculateStatisticsService;
import com.studio.rain.service.GenerateDefaultJSON;
import com.studio.rain.service.XMLStatisticsWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Random;


/**
 * Facade class providing simplified access to statistical calculations and file generation.
 */
public class StatisticsFacade {
    private final CalculateStatisticsService calculateStatisticsService = new CalculateStatisticsService();
    private final GenerateDefaultJSON generateDefaultJSON = new GenerateDefaultJSON();

    /**
     * Initiates the calculation of statistics based on JSON files in a specified folder.
     *
     * @param reader        BufferedReader for user input.
     * @param folderPath    The path to the folder containing the JSON files.
     * @param attributeName The attribute name to be used for calculating statistics.
     * @throws IllegalArgumentException if the folder path is null.
     */
    public void startCalculateStatistics(BufferedReader reader, String folderPath, String attributeName) {
        if (folderPath != null) {
            try {
                System.out.print("Input number of threads that you want to use: ");
                String numOfThread = reader.readLine();
                long startTime = System.currentTimeMillis();
                Map<String, Integer> statistics = calculateStatisticsService.calculateStatistics(folderPath, attributeName, Integer.parseInt(numOfThread));
                long endTime = System.currentTimeMillis();
                System.out.println("Statistic length: " + statistics.size());
                System.out.println("Time for numOfThreads(" + numOfThread + "): " + (endTime - startTime) + " ms");
                saveStatisticToFile(statistics, folderPath, attributeName);
            } catch (IOException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        } else {
            throw new IllegalArgumentException("Folder path is null");
        }
    }

    /**
     * Saves statistics to an XML file.
     *
     * @param statistics    The statistics to be saved.
     * @param folderPath    The path to the folder where the XML file will be saved.
     * @param attributeName The attribute name used for calculating the statistics.
     * @throws IllegalArgumentException if an error occurs during file writing.
     */
    public void saveStatisticToFile(Map<String, Integer> statistics, String folderPath, String attributeName) {
        try {
            System.out.println("Statistics by \"" + attributeName + "\":");
            XMLStatisticsWriter xmlStatisticsWriter = new XMLStatisticsWriter();
            xmlStatisticsWriter.writeStatisticsToXML(statistics, folderPath, attributeName);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }

    }

    /**
     * Creates a JSON file with randomly generated input statistics.
     *
     * @param folderPath The path to the folder where the JSON file will be created.
     * @throws IllegalArgumentException if an error occurs during file creation.
     */
    public void createFileWithRandomInputStatistics(String folderPath) {
        Random random = new Random();

        folderPath = folderPath + "/example_generated_file_" + random.nextInt() + ".json";
        File file = new File(folderPath);
        try {
            if (!file.exists()) {
                if (file.createNewFile()) {
                    generateDefaultJSON.generateStartJSON(file);
                } else {
                    System.out.println("Error creating file");
                }
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
