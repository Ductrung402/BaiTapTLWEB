package murach.business;

import java.io.Serializable;

public class User implements Serializable {

    // 6 thuộc tính tương ứng với 6 trường trong khảo sát
    private String firstName;
    private String lastName;
    private String email;
    private String dob;
    private String heardFrom;
    private String wantsUpdates;
    private String contactVia;

    // Constructor mặc định (no-argument constructor)
    public User() {
        firstName = "";
        lastName = "";
        email = "";
        dob = "";
        heardFrom = "";
        wantsUpdates = "";
        contactVia = "";
    }

    // Constructor đầy đủ tham số
    public User(String firstName, String lastName, String email, 
                String dob, String heardFrom, String wantsUpdates, String contactVia) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dob = dob;
        this.heardFrom = heardFrom;
        this.wantsUpdates = wantsUpdates;
        this.contactVia = contactVia;
    }

    // Getters và Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getHeardFrom() {
        return heardFrom;
    }

    public void setHeardFrom(String heardFrom) {
        this.heardFrom = heardFrom;
    }

    public String getWantsUpdates() {
        return wantsUpdates;
    }

    public void setWantsUpdates(String wantsUpdates) {
        this.wantsUpdates = wantsUpdates;
    }

    public String getContactVia() {
        return contactVia;
    }

    public void setContactVia(String contactVia) {
        this.contactVia = contactVia;
    }
}