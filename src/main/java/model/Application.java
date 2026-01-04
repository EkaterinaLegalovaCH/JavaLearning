package model;

//Class model.Application created as blueprint for creating objects
public class Application {

    public String company;
    public String position;
    public String status;
    public int Id;


    // constructor of model.Application class to initialize an object
    public Application(String company, String position, String status) {
        this.company = company;
        this.position = position;
        this.status = status;

    }


    public String prettyPrint() {
        return "ID: " + Id +
                " | " + company +
                " | " + position +
                " | " + status;
    }

}
