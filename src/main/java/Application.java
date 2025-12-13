import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashSet;

//Class Application created as blueprint for creating objects
public class Application {

    public String company;
    public String position;
    public String status;
    public int Id;


    // constructor of Application class to initialize an object
    public Application(String company, String position, String status) {
        this.company = company;
        this.position = position;
        this.status = status;

    }

    // method created to update status of application
    public void ApdateStatus(String newStatus) {
        this.status = newStatus;
    }

    // method to print details of application
    public String PrintDetails() {
        return company + " - " + position + "(status: " + status + ")";
    }

    public List<String> ApplicationAsList() {
        return Arrays.asList(
                this.company,
                this.position,
                this.status

        );
    }


}
