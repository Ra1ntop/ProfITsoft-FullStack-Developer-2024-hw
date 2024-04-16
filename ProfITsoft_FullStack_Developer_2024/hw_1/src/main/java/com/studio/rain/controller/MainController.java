package com.studio.rain.controller;

import com.studio.rain.facade.StatisticsFacade;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainController {
    private final StatisticsFacade statisticsFacade = new StatisticsFacade();
    public boolean isDir = false;
    public String folderPath = null;
    private String attributeName = null;

    public void start(String folderPath, String attributeName) {
        if (isPathCorrect(folderPath)) {
            this.attributeName = attributeName;
            setIsDir(folderPath);
            list();

        } else {
            System.out.println("The path is not correct!");
            System.exit(-1);
        }
    }

    private void list() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("---------------------------------------------");
            System.out.println("--------------------MENU---------------------");
            System.out.println("1. Create File With Random Input Statistics");
            System.out.println("2. Read the file(-s) and create report in xml format");
            System.out.println("9. Exit");
            System.out.println("---------------------------------------------");

            int choice = 0;
            try {
                choice = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input ");
                continue;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            switch (choice) {
                case 1:
                    statisticsFacade.createFileWithRandomInputStatistics(folderPath);
                    break;
                case 2:
                    statisticsFacade.startCalculateStatistics(reader, folderPath, attributeName);
                    break;

                case 9:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Try again");
            }
        }
    }


    private void setIsDir(String folderPath) {
        File folder = new File(folderPath);
        this.isDir = folder.isDirectory();
    }

    private boolean isPathCorrect(String folderPath) {
        if (!folderPath.endsWith(File.separator)) {
            folderPath += File.separator;
        }
        File folder = new File(folderPath);
        if (folder.exists()) {
            this.folderPath = folderPath;
            System.out.println("folderPath = " + this.folderPath);
            return folder.exists();

        } else {
            return false;
        }
    }


}
