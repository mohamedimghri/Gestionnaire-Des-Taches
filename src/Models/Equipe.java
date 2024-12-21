package Models;


public class Equipe {
    private int id;
    private String name;
    private int members;
    private Tache tache ;
    private String deadline;

    public Equipe(String name,int members,Tache tache ,String deadline) {
        this.name = name;
        this.members = members ;
        this.tache = tache;
        this.deadline = deadline;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMembers() {
        return members;
    }

    public void setMembers(int members) {
        this.members = members;
    }


    public Tache getTache() {
        return tache;
    }

    public void setTache(Tache tache) {
        this.tache = tache;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
    

}