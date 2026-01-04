package service;

import model.Application;

import java.util.List;
import java.util.Iterator;



public class JobBoard {
    private final List<Application> applications;
    private final PersistenceService persistenceService;

    public JobBoard(List<Application> applications, PersistenceService persistenceService) {
        this.applications = applications;
        this.persistenceService = persistenceService;
    }

    public void addApplication(Application app) {
        app.Id = IdGenerator.getNextId();
        applications.add(app);
        persistenceService.saveApplications(applications);

    }

    public boolean deleteById(int id) {
        Iterator<Application> iterator = applications.iterator();

        while (iterator.hasNext()) {
            Application app = iterator.next();

            if (app.Id == id) {
                iterator.remove();
                persistenceService.saveApplications(applications);
                return true;
            }
        }
        return false;
    }

    public boolean updateStatusById(int id, String newStatus) {
        for (Application app : applications) {
            if (app.Id == id) {
                app.status = newStatus;
                persistenceService.saveApplications(applications);
                return true;
            }
        }
        return false;
    }

    public void printAllApplications() {
        if (applications.isEmpty()) {
            System.out.println("List of applications is empty!");
            return;
        }
        for (Application app : applications) {
            System.out.println(app.prettyPrint());
        }
    }

    public List<Application> getApplications(){
        return applications;
    }

}
