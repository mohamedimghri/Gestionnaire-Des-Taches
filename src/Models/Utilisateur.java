package Models;


public class Utilisateur {
    private int id;
    private String name;
    private String email;
    private String password;
    private String tachetitle;
    private String deadline;

    public Utilisateur( String name, String email,String password, String deadline,String tachetitle) {
        this.name = name;
        this.email = email;
        this.deadline = deadline;
        this.tachetitle = tachetitle;
    }

    
    public int getId() {
        return id; 
    }
    public String getName() { 
        return name; 
    }
    public String getEmail() { 
        return email; 
    }
    public String getPassword() {
        return password;
    }
    public String getDeadline() {
        return deadline;
    }
    public String getTachetitle() {
        return tachetitle;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public void setTachetitle(String tachetitle) {
        this.tachetitle = tachetitle;
    }


    public void setDeadline(String deadline) {
        this.deadline = deadline;
    } 
}

