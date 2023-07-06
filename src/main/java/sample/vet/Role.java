package sample.vet;


public class Role {
    private long role_id;
    private String name;
    public Role(long role_id, String name){
        this.role_id = role_id;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setRole_id(long role_id) {
        this.role_id = role_id;
    }

    public long getRole_id() {
        return role_id;
    }
}

