package sample.vet;


public class Disease {
    private long disease_id;
    private String common_name;
    private String scientific_name;
    public Disease(int disease_id, String common_name, String scientific_name){
        this.disease_id = disease_id;
        this.common_name = common_name;
        this.scientific_name = scientific_name;
    }
    public Disease(String common_name, String scientific_name){
        this.common_name = common_name;
        this.scientific_name = scientific_name;
    }

    public long getDisease_id() {
        return disease_id;
    }

    public String getCommon_name() {
        return common_name;
    }

    public String getScientific_name() {
        return scientific_name;
    }

    public void setDisease_id(long disease_id) {
        this.disease_id = disease_id;
    }

    public void setCommon_name(String common_name) {
        this.common_name = common_name;
    }

    public void setScientific_name(String scientific_name) {
        this.scientific_name = scientific_name;
    }
}
