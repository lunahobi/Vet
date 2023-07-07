package sample.vet;
;

public class Doctor extends User{
    private int doctor_id;
    private String last_name;
    private String first_name;
    private String second_name;
    private String address;
    private String phone_number;
    public Doctor(int doctor_id, String last_name, String first_name, String second_name, String address, String phone_number, String login, String password){
        super(login, password, 1);
        this.doctor_id = doctor_id;
        this.last_name = last_name;
        this.first_name = first_name;
        this.second_name = second_name;
        this.address = address;
        this.phone_number = phone_number;
    }
    public Doctor(String last_name, String first_name, String second_name, String address, String phone_number, String login, String password){
        super(login, password, 1);
        this.last_name = last_name;
        this.first_name = first_name;
        this.second_name = second_name;
        this.address = address;
        this.phone_number = phone_number;
    }


    public int getDoctor_id() {
        return doctor_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id= doctor_id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

}
