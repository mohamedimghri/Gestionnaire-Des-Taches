package Models;

public class Tache{
    private int id;
    private String title ;
    private String description ;
    private Priorité priorité;
    private Status status;
    public Tache(int id, String title, String description, Priorité priorité, Status status ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priorité = priorité;
        this.status = status;
    }
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Priorité getPriorité() {
        return priorité;
    }
    public void setPriorité(Priorité priorité) {
        this.priorité = priorité;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }   
}