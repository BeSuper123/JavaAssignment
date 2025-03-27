/*
 Description: the purpose of this class is to hold all the details of the frequency table
 Author: Blesisng Ugochukwu
 Date: 27/03/25
 */
public class Student {
    // attributes
    private String attendance;
    private String job;
    private String submissions;
    private String studyhours;
    private String graduated;

    // constructor
    public Student (String attendance, String job, String submissions, String studyhours, String graduated) {
        setAttendance(attendance);
        setJob(job);
        setSubmissions(submissions);
        setStudyhours(studyhours);
        setGraduated(graduated);
    }


    // getters and setters
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

    @Override
    public String toString() {
        return getAttendance() + "," + getJob() + "," + getSubmissions() + "," + getStudyhours() + "," + getGraduated();
    }

    
}