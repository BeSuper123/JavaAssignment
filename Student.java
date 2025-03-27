/*
 Description: the purpose of this class is to hold all the details of the frequency table
*/
public class Student {
    private String attendance;
    private String job;
    private String submissions;
    private String studyhours;
    private String graduated;

    public Student (String attendance, String job, String submissions, String studyhours, String graduated) {
        
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getSubmissions() {
        return submissions;
    }

    public void setSubmissions(String submissions) {
        this.submissions = submissions;
    }

    public String getStudyhours() {
        return studyhours;
    }

    public void setStudyhours(String studyhours) {
        this.studyhours = studyhours;
    }

    public String getGraduated() {
        return graduated;
    }

    public void setGraduated(String graduated) {
        this.graduated = graduated;
    }
}