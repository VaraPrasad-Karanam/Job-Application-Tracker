package JobApplicationTracker;

import java.sql.Date;
import java.util.Scanner;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        JobApplicationDAO dao = new JobApplicationDAO();

        int choice;

        do {
            System.out.println("\n========== JOB APPLICATION TRACKER ==========");
            System.out.println("1. Add Application");
            System.out.println("2. View All Applications");
            System.out.println("3. Search Application by ID");
            System.out.println("4. Update Application Status");
            System.out.println("5. Delete Application");
            System.out.println("6. Filter Applications by Status");
            System.out.println("7. Exit");
            System.out.println("8. Application Summary");
            System.out.print("Enter your choice: ");

            choice = getIntegerInput(sc); // Consume the leftover newline

            try {
                switch (choice) {

                    case 1:
                        System.out.print("Enter company name: ");
                        String company = sc.nextLine();

                        System.out.print("Enter job role: ");
                        String role = sc.nextLine();

                        Date appliedDate = getValidDate(sc);

                        String status = getValidStatus(sc);

                        JobApplication app = new JobApplication(
                                0, company, role, appliedDate, status
                        );

                        dao.addApplication(app);
                        break;

                    case 2:
                    List<JobApplication> applications = dao.viewApplications();

                    if (applications.isEmpty()) {
                        System.out.println("No applications found!");
                    } else {
                        System.out.println("\n========== ALL JOB APPLICATIONS ==========");

                        for (JobApplication application : applications) {
                            System.out.println("\nID: " + application.getId());
                            System.out.println("Company: " + application.getCompanyName());
                            System.out.println("Role: " + application.getJobRole());
                            System.out.println("Applied Date: " + application.getAppliedDate());
                            System.out.println("Status: " + application.getStatus());
                            System.out.println("--------------------------------");
                        }
                    }
                    break;

                    case 3:
                        System.out.print("Enter application ID: ");
                        int searchId = getIntegerInput(sc);

                        dao.searchApplication(searchId);
                        break;

                    case 4:
                        System.out.print("Enter application ID: ");
                        int updateId = sc.nextInt();
                        sc.nextLine();

                        String newStatus = getValidStatus(sc);

                        dao.updateApplication(updateId, newStatus);
                        break;

                    case 5:
                        System.out.print("Enter application ID: ");
                        int deleteId = sc.nextInt();
                        sc.nextLine();

                        dao.deleteApplication(deleteId);
                        break;

                    case 6:
                        System.out.print("Enter status to filter: ");
                        String filterStatus = sc.nextLine();

                        dao.filterByStatus(filterStatus);
                        break;

                    case 7:
                        System.out.println("Thank you for using Job Application Tracker!");
                        break;
                    case 8:
                        dao.showApplicationSummary();
                        break;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (choice != 7);

        sc.close();
    }
        public static String getValidStatus(Scanner sc) {

        while (true) {

            System.out.println("\nSelect Application Status:");
            System.out.println("1. Applied");
            System.out.println("2. Assessment");
            System.out.println("3. Interview");
            System.out.println("4. Selected");
            System.out.println("5. Rejected");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    return "Applied";
                case 2:
                    return "Assessment";
                case 3:
                    return "Interview";
                case 4:
                    return "Selected";
                case 5:
                    return "Rejected";
                default:
                    System.out.println("Invalid status! Please try again.");
            }
        }
    }
        public static int getIntegerInput(Scanner sc) {

        while (!sc.hasNextInt()) {
            System.out.print("Invalid input! Please enter a number: ");
            sc.next(); 
        }

        int number = sc.nextInt();
        sc.nextLine();

        return number;
    }
        public static Date getValidDate(Scanner sc) {

        while (true) {
            System.out.print("Enter applied date (yyyy-MM-dd): ");
            String dateInput = sc.nextLine();

            try {
                return Date.valueOf(dateInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid date! Please use yyyy-MM-dd format.");
            }
        }
    }
}