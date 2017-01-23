package Dao;

import javax.persistence.*;


@Entity
@Table(name = "userdetails")
@SequenceGenerator(name="seq", initialValue=2, allocationSize=1)

public class UserDetailsDao {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq")
    @Column(columnDefinition = "INTEGER")
    private int id;
    @Column(columnDefinition = "VARCHAR(100)")
    private String firstName;
    @Column(columnDefinition = "VARCHAR(100)")
    private String lastName;
    @Column(columnDefinition = "VARCHAR(100)")
    private String email;
    @Column(columnDefinition = "VARCHAR(100)")
    private String phone;
    @Column(columnDefinition = "VARCHAR(100)")
    private String password;


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}



