package sample.vet;


import java.sql.Time;
import java.util.Date;

public class Appointment {
    private long appointment_id;
    private long animal_id;
    private long owner_id;
    private long doctor_id;
    private Date date;
    private Time time;

    public Appointment(long appointment_id, long animal_id, long owner_id, long doctor_id, Date date, Time time) {
        this.appointment_id = appointment_id;
        this.animal_id = animal_id;
        this.owner_id = owner_id;
        this.doctor_id = doctor_id;
        this.date = date;
        this.time = time;
    }
    public Appointment(long animal_id, long owner_id, long doctor_id, Date date, Time time) {
        this.animal_id = animal_id;
        this.owner_id = owner_id;
        this.doctor_id = doctor_id;
        this.date = date;
        this.time = time;
    }

    public long getAppointment_id() {
        return appointment_id;
    }

    public long getAnimal_id() {
        return animal_id;
    }

    public long getOwner_id() {
        return owner_id;
    }

    public long getDoctor_id() {
        return doctor_id;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public void setAppointment_id(long appointment_id) {
        this.appointment_id = appointment_id;
    }

    public void setAnimal_id(long animal_id) {
        this.animal_id = animal_id;
    }

    public void setDoctor_id(long doctor_id) {
        this.doctor_id = doctor_id;
    }

    public void setOwner_id(long owner_id) {
        this.owner_id = owner_id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}

