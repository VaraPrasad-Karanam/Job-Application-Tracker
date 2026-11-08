package JobApplicationTracker;

import java.sql.Date;

public class JobApplication {

    private int id;
    private String companyName;
    private String jobRole;
    private Date appliedDate;
    private String status;

    // Constructor
    public JobApplication(int id, String companyName, String jobRole,
                          Date appliedDate, String status) {

        this.id = id;
        this.companyName = companyName;
        this.jobRole = jobRole;
        this.appliedDate = appliedDate;
        this.status = status;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getJobRole() {
        return jobRole;
    }

    public Date getAppliedDate() {
        return appliedDate;
    }

    public String getStatus() {
        return status;
    }
}
