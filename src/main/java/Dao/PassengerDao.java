package Dao;


import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "passenger")
public class PassengerDao {
    @Id
    private long id;
    private Timestamp timestamp;
    private String name;
    private String gender;
    private int age;
    private String seat;
    private char isSeniorCitizen;
    private char isDisabled;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }


    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public char getIsSeniorCitizen() {
        return isSeniorCitizen;
    }

    public void setIsSeniorCitizen(char isSeniorCitizen) {
        this.isSeniorCitizen = isSeniorCitizen;
    }

    public char getIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(char isDisabled) {
        this.isDisabled = isDisabled;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
