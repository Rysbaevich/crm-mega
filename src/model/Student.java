package model;

import java.time.LocalDate;

public class Student extends User {
    private LocalDate dob;

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
}
