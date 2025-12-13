import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        PersistenceService storage = new PersistenceService();

        List<Application> jsonApplications = storage.loadApplications();

        int maxId = 0;

        for (Application app : jsonApplications) {
            if (app.Id > maxId) {
                maxId = app.Id;
            }
        }

        IdGenerator.setStartingId(maxId);


        //new obj app1 created
        /*Application app1 = new Application("Google", "web developer", "Applied");
        Application app2 = new Application("Spotify", "Backend Developer", "Applied");
        Application app3 = new Application("Amazon", "Frontend Engineer", "Interview");

         */



        /*
        app1.Id = IdGenerator.getNextId();
        jsonApplications.add(app1);
        app2.Id = IdGenerator.getNextId();
        jsonApplications.add(app2);
        app3.Id = IdGenerator.getNextId();
        jsonApplications.add(app3);

         */

        JobBoard jobBoard = new JobBoard(jsonApplications);

        boolean deleted = jobBoard.deleteById(6);
        if (deleted) {
            System.out.println("Application deleted");
        } else {
            System.out.println("Application with this Id was not found");
        }
        storage.saveApplications(jsonApplications);


    }
}
