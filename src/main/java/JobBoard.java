import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;



public class JobBoard {

    private List<Application> applications;

    public JobBoard(List<Application> applications) {
        this.applications = applications;
    }

    public boolean deleteById(int id) {
        Iterator<Application> iterator = applications.iterator();

        while (iterator.hasNext()) {
            Application app = iterator.next();

            if (app.Id == id) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public List<Application> getApplications(){
        return applications;
    }

}
