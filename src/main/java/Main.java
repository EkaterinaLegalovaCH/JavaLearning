import model.Application;
import service.IdGenerator;
import service.JobBoard;
import service.PersistenceService;

import java.util.List;

public class Main {
    public static void main(String[] args) {


        PersistenceService storage = new PersistenceService();

        List<Application> jsonApplications = storage.loadApplications();

        IdGenerator.initialize(jsonApplications);




        JobBoard jobBoard = new JobBoard(jsonApplications);

        Application app = new Application("Spotify", "Web Developer", "Applied");
        jobBoard.addApplication(app);


        storage.saveApplications(jsonApplications);

        jobBoard.printAllApplications();


    }
}
