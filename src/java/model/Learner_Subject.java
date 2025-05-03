package model;
import java.util.Date;

public class Learner_Subject {

    private String id;
    private String learner_id;
    private String subject_id;
    private Date enrolled_date;
    private String active;
    private String status_id;
    private String account_id;
    private String fullname;
    private String subject_name;
    private String image;
    private String lecturer_id;
    private String lecturer_name;
    private String category_id;
    private String description;
    private String percent;

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public Learner_Subject(String id, String learner_id, String subject_id, Date enrolled_date, String status_id, String fullname, String subject_name, String description, String percent) {
        this.id = id;
        this.learner_id = learner_id;
        this.subject_id = subject_id;
        this.enrolled_date = enrolled_date;
        this.status_id = status_id;
        this.fullname = fullname;
        this.subject_name = subject_name;
        this.description = description;
        this.percent = percent;
    }
    

    public Learner_Subject() {
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public Learner_Subject(String id, String learner_id, String subject_id, Date enrolled_date, String status_id) {
        this.id = id;
        this.learner_id = learner_id;
        this.subject_id = subject_id;
        this.enrolled_date = enrolled_date;
        this.status_id = status_id;
    }

    public Learner_Subject(String id, String learner_id, String subject_id, Date enrolled_date, String status_id, String subject_name, String description, String fullname) {
        this.id = id;
        this.learner_id = learner_id;
        this.subject_id = subject_id;
        this.enrolled_date = enrolled_date;
        this.status_id = status_id;
        this.subject_name = subject_name;
        this.description = description;
        this.fullname = fullname;
    }

    public Learner_Subject(String id, String learner_id, String subject_id, Date enrolled_date, String active, String status_id) {
        this.id = id;
        this.learner_id = learner_id;
        this.subject_id = subject_id;
        this.enrolled_date = enrolled_date;
        this.active = active;
        this.status_id = status_id;
    }

    public Date getEnrolled_date() {
        return enrolled_date;
    }

    public void setEnrolled_date(Date enrolled_date) {
        this.enrolled_date = enrolled_date;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLecturer_id() {
        return lecturer_id;
    }

    public void setLecturer_id(String lecturer_id) {
        this.lecturer_id = lecturer_id;
    }

    public String getLecturer_name() {
        return lecturer_name;
    }

    public void setLecturer_name(String lecturer_name) {
        this.lecturer_name = lecturer_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLearner_id() {
        return learner_id;
    }

    public void setLearner_id(String learner_id) {
        this.learner_id = learner_id;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getStatus_id() {
        return status_id;
    }

    public void setStatus_id(String status_id) {
        this.status_id = status_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    @Override
    public String toString() {
        return "Learner_Subject{" + "id=" + id + ", learner_id=" + learner_id + ", subject_id=" + subject_id + ", enrolled_date=" + enrolled_date + ", active=" + active + ", status_id=" + status_id + ", account_id=" + account_id + ", fullname=" + fullname + ", subject_name=" + subject_name + ", image=" + image + ", lecturer_id=" + lecturer_id + ", lecturer_name=" + lecturer_name + ", category_id=" + category_id + '}';
    }

}