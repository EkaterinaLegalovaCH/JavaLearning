import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PersistenceService {

    private static final String FILE_PATH = "applications.json";
    private final Gson gson = new Gson();

    public void saveApplications(List<Application> applications) {
        try (FileWriter writer = new FileWriter(FILE_PATH)){
            gson.toJson(applications, writer);
            System.out.println("Applications saved to " + FILE_PATH);
        } catch (IOException e) {
            System.out.println("Error saving applications " + e.getMessage());
        }
    }

    public List<Application> loadApplications() {
        try(FileReader reader = new FileReader(FILE_PATH)) {
            Application[] array = gson.fromJson(reader, Application[].class);
            return new ArrayList<>(Arrays.asList(array));
        } catch (IOException e) {
            System.out.println("No saved applications found!" + e);
            return new ArrayList<>();
        }
    }

}
