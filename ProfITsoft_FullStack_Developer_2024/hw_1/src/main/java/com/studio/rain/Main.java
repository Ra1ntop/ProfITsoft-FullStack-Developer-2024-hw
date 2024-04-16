package com.studio.rain;


import com.studio.rain.controller.MainController;

public class Main {
    public static void main(String[] args) {
        if (args != null) {
            String folderPath = args[0];
            String attributeName = args[1];
            MainController mainController = new MainController();
            mainController.start(folderPath, attributeName);

        }
    }

}

