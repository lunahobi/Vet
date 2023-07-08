package sample.vet;


public class Appointment {
    private int id;
    private String doctor_name;
    private int animal_id;
    private String animal_name;
    private String date;
    private String time;

    public Appointment(int id, String doctorName, int petId, String petName, String date, String time) {
        this.id = id;
        this.doctor_name = doctorName;
        this.animal_id = petId;
        this.animal_name = petName;
        this.date = date;
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

    public String getDoctor_name() {
        return doctor_name;
    }

    public String getAnimal_name() {
        return animal_name;
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

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public void setAnimal_id(int animal_id) {
        this.animal_id = animal_id;
    }

    public void setAnimal_name(String animal_name) {
        this.animal_name = animal_name;
    }
}
