package model;

import java.time.LocalDate;
import java.util.List;

public class Student extends User {
    private LocalDate dob;
    private List<Group> groups;

    @Override
    public String toString() {
        return "Student{" +
                "dob=" + dob +
                ", groups=" + groups +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                ", dateCreated=" + dateCreated +
                '}';
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
}
