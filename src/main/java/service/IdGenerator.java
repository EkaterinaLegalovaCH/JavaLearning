package service;

import model.Application;

import java.util.List;

public class IdGenerator {
    private static int currentId = 1;

    public static int getNextId() {
        return currentId++;
    }

    public static void initialize(List<Application> applications) {
        int maxId = 0;

        for (Application app : applications) {
            if (app.Id > maxId) {
                maxId = app.Id;
            }
        }
        currentId = maxId + 1;
    }


}
