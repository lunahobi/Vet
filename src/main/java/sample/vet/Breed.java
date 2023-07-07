package sample.vet;

public class Breed {
    private long breed_id;
    private String name;
    public Breed(long breed_id, String name){
        this.breed_id = breed_id;
        this.name = name;
    }
    public Breed(String name){
        this.name = name;
    }

    public long getBreed_id() {
        return breed_id;
    }

    public String getName() {
        return name;
    }

    public void setBreed_id(long breed_id) {
        this.breed_id = breed_id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
