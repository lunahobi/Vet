package sample.vet;

public class AppointmentDoctor {
    private int id;
    private String owner_name;
    private int animal_id;
    private String breed;
    private String animal_name;
    private String date;
    private String time;

    public AppointmentDoctor(String ownerName, int petId, String petName, String breed, String date, String time) {
        this.owner_name = ownerName;
        this.animal_id = petId;
        this.breed = breed;
        this.animal_name = petName;
        this.date = date;
        this.time = time;
    }
    public AppointmentDoctor(String ownerName, int petId, String petName, String breed, String time) {
        this.owner_name = ownerName;
        this.animal_id = petId;
        this.breed = breed;
        this.animal_name = petName;
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public String getAnimal_name() {
        return animal_name;
    }

    public String getBreed() {
        return breed;
    }

    public int getAnimal_id(){
        return animal_id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOwner_name(String doctor_name) {
        this.owner_name = doctor_name;
    }

    public void setAnimal_id(int animal_id) {
        this.animal_id = animal_id;
    }

    public void setAnimal_name(String animal_name) {
        this.animal_name = animal_name;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
}
