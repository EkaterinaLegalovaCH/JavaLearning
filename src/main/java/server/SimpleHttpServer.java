package server;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpServer;
import model.Application;
import service.JobBoard;
import service.PersistenceService;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.List;

public class SimpleHttpServer {
    private final JobBoard jobBoard;
    private final Gson gson = new Gson();
    PersistenceService storage = new PersistenceService();

    public SimpleHttpServer(JobBoard jobBoard) {
        this.jobBoard = jobBoard;
    }

    public void start() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        List<Application> applications = jobBoard.getApplications();
        server.createContext("/applications", exchange -> {
            if ("GET".equals(exchange.getRequestMethod())) {
                String json = gson.toJson(applications);

                exchange.getResponseHeaders().add("Content-Type", "application/json");
                exchange.sendResponseHeaders(200, json.getBytes().length);

                OutputStream os = exchange.getResponseBody();
                os.write(json.getBytes());
                os.close();
            } else if ("POST".equals(exchange.getRequestMethod())) {
                InputStream newApplication =  exchange.getRequestBody();
                InputStreamReader input = new InputStreamReader(newApplication);
                BufferedReader br = new BufferedReader(input);
                StringBuilder sb = new StringBuilder("");
                try {
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                        System.out.println(line);
                    }
                    String text = sb.toString();
                    Application newApp = gson.fromJson(text, Application.class);
                    jobBoard.addApplication(newApp);
                    storage.saveApplications(applications);

                    exchange.sendResponseHeaders(201, -1);
                    exchange.getResponseBody().close();
                    return;
                } catch (IOException e) {
                    System.out.println("Error");
                }

            } else if ("DELETE".equals(exchange.getRequestMethod())) {
                    InputStream idToDelete = exchange.getRequestBody();
                    InputStreamReader input = new InputStreamReader(idToDelete);
                    BufferedReader br = new BufferedReader(input);
                    int idToDel = Integer.parseInt(br.readLine());
                    jobBoard.deleteById(idToDel);
                    storage.saveApplications(applications);

                    exchange.sendResponseHeaders(204, -1);
                    exchange.close();
                    return;

            } else {
                exchange.sendResponseHeaders(405, -1);
                return;
            }

        });

        server.start();
        System.out.println("Server started at http://localhost:8080/applications");
    }

}
