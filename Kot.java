package kobietydokoukurs;

import java.util.Date;

public class Kot {

    private String name;
    private Date dateOfBirth;
    private Float weight;
    private String guardianName;


    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) { this.weight = weight; }

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) { this.guardianName = guardianName; }
}

