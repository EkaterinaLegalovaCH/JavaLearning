import model.Application;
import service.IdGenerator;
import service.*;
import ui.ConsoleUI;

import java.util.List;


public class Main {
    public static void main(String[] args) {

        PersistenceService storage = new PersistenceService();
        List<Application> jsonApplications = storage.loadApplications();

        IdGenerator.initialize(jsonApplications);

        JobBoard jobBoard = new JobBoard(jsonApplications);
        ConsoleUI ui = new ConsoleUI(jobBoard);

        ui.start();

        storage.saveApplications(jsonApplications);

    }
}
