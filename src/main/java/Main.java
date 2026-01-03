import model.Application;
import service.IdGenerator;
import service.*;
import ui.ConsoleUI;
import server.SimpleHttpServer;

import java.util.List;


public class Main {
    public static void main(String[] args) throws Exception {

        PersistenceService storage = new PersistenceService();
        List<Application> jsonApplications = storage.loadApplications();

        IdGenerator.initialize(jsonApplications);

        JobBoard jobBoard = new JobBoard(jsonApplications);
        SimpleHttpServer server = new SimpleHttpServer(jobBoard);
        server.start();

    }
}
