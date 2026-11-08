package JobApplicationTracker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobApplicationDAO {

    // 1. Add Job Application
    public void addApplication(JobApplication app) throws SQLException {

        String query = "INSERT INTO applications "
                + "(company_name, job_role, applied_date, status) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setString(1, app.getCompanyName());
            pstmt.setString(2, app.getJobRole());
            pstmt.setDate(3, app.getAppliedDate());
            pstmt.setString(4, app.getStatus());

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Application added successfully!");
            }
        }
    }

    // 2. View All Applications
    public List<JobApplication> viewApplications() throws SQLException {

    List<JobApplication> applications = new ArrayList<>();

    String query = "SELECT * FROM applications";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement pstmt = con.prepareStatement(query);
         ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {

                JobApplication app = new JobApplication(
                    rs.getInt("id"),
                    rs.getString("company_name"),
                    rs.getString("job_role"),
                    rs.getDate("applied_date"),
                    rs.getString("status")
                );

                applications.add(app);
            }
        }

        return applications;
    }

    // 3. Update Application Status
    public void updateApplication(int id, String newStatus) throws SQLException {

        String query = "UPDATE applications SET status = ? WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setString(1, newStatus);
            pstmt.setInt(2, id);

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Application status updated successfully!");
            } else {
                System.out.println("Application ID not found!");
            }
        }
    }

    // 4. Delete Job Application
    public void deleteApplication(int id) throws SQLException {

        String query = "DELETE FROM applications WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, id);

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Application deleted successfully!");
            } else {
                System.out.println("Application ID not found!");
            }
        }
    }
    // 5. Search Application by ID
    public void searchApplication(int id) throws SQLException {

        String query = "SELECT * FROM applications WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {

                    System.out.println("\nID: " + rs.getInt("id"));
                    System.out.println("Company: " + rs.getString("company_name"));
                    System.out.println("Role: " + rs.getString("job_role"));
                    System.out.println("Applied Date: " + rs.getDate("applied_date"));
                    System.out.println("Status: " + rs.getString("status"));

                } else {
                    System.out.println("Application ID not found!");
                }
            }
        }
    }

    // 6. Filter Applications by Status
    public void filterByStatus(String status) throws SQLException {

        String query = "SELECT * FROM applications WHERE status = ?";

        try (Connection con = DBConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setString(1, status);

            try (ResultSet rs = pstmt.executeQuery()) {

                boolean found = false;

                while (rs.next()) {

                    found = true;

                    System.out.println("\nID: " + rs.getInt("id"));
                    System.out.println("Company: " + rs.getString("company_name"));
                    System.out.println("Role: " + rs.getString("job_role"));
                    System.out.println("Applied Date: " + rs.getDate("applied_date"));
                    System.out.println("Status: " + rs.getString("status"));

                    System.out.println("----------------------------");
                }

                if (!found) {
                    System.out.println("No applications found with status: " + status);
                }
            }
        }
    }
        public void showApplicationSummary() throws SQLException {

        String query = "SELECT status, COUNT(*) AS total " +
                    "FROM applications GROUP BY status";

        try (Connection con = DBConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery()) {

            System.out.println("\n========== APPLICATION SUMMARY ==========");

            int totalApplications = 0;

            while (rs.next()) {

                String status = rs.getString("status");
                int count = rs.getInt("total");

                System.out.println(status + ": " + count);

                totalApplications += count;
            }

            System.out.println("--------------------------------");
            System.out.println("Total Applications: " + totalApplications);
        }
    }
}