package ui;

import model.Application;
import service.JobBoard;

import java.util.Scanner;

public class ConsoleUI {

    private final JobBoard jobBoard;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleUI(JobBoard jobBoard) {
        this.jobBoard = jobBoard;
    }

    public void start() {
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt();

            switch (choice) {
                case 1 -> jobBoard.printAllApplications();
                case 2 -> addApplication();
                case 3 -> updateStatus();
                case 4 -> deleteApplication();
                case 0 -> running = false;
                default -> System.out.println("Invalid option!");
            }
        }

        System.out.println("Exiting application.");
    }

    private void printMenu() {
        System.out.println("\n=== Job Application Tracker ===");
        System.out.println("1. Show all applications");
        System.out.println("2. Add application");
        System.out.println("3. Update status");
        System.out.println("4. Delete application");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    private void addApplication() {
        System.out.println("Company: ");
        String company = scanner.nextLine();

        System.out.println("Position: ");
        String position = scanner.nextLine();

        System.out.println("Status: ");
        String status = scanner.nextLine();

        jobBoard.addApplication(new Application(company, position, status));
        System.out.println("Application added successfully.");
    }

    private void updateStatus() {
        System.out.println("Enter Id of application you want to update: ");
        int idToUpdate = readInt();

        System.out.println("New status: ");
        String newStatus = scanner.nextLine();

        jobBoard.updateStatusById(idToUpdate, newStatus);
    }

    private void deleteApplication() {
        System.out.println("Enter Id of application you want to delete: ");
        int idToDelete = readInt();

        jobBoard.deleteById(idToDelete);
    }

    private int readInt() {
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

}
