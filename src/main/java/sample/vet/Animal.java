package sample.vet;

public class Animal {
    private long animal_id;
    private  long owner_id;
    private long breed_id;
    private String name;
    public Animal(long animal_id, Long owner_id, long breed_id, String name){
        this.animal_id = animal_id;
        this.owner_id = owner_id;
        this.breed_id = breed_id;
        this.name = name;
    }
    public Animal(Long owner_id, long breed_id, String name){
        this.owner_id = owner_id;
        this.breed_id = breed_id;
        this.name = name;
    }

    public long getAnimal_id() {
        return animal_id;
    }

    public long getOwner_id() {
        return owner_id;
    }

    public long getBreed_id() {
        return breed_id;
    }

    public String getName() {
        return name;
    }

    public void setAnimal_id(long animal_id) {
        this.animal_id = animal_id;
    }

    public void setOwner_id(long owner_id) {
        this.owner_id = owner_id;
    }

    public void setBreed_id(long breed_id) {
        this.breed_id = breed_id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
