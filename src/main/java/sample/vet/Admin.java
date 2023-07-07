package sample.vet;

public class Admin extends User{
    private String name;
    public Admin(String name, String password, String login){
        super(login, password, 3);
        this.name = name;
    }
}
