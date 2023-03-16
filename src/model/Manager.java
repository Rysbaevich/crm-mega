package model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Manager extends User {
    private double salary;

    public Manager(String name, String surname, String phone, String email, double salary) {
        super(name, surname, phone, email);
        this.salary = salary;
    }


    public static class ManagerBuilder {

        private String name;
        private String surname;
        private String phone;
        private String email;
        private double salary;

        private ManagerBuilder() {
        }

        public static ManagerBuilder builder() {
            return new ManagerBuilder();
        }

        public ManagerBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ManagerBuilder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public ManagerBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public ManagerBuilder email(String email) {
            this.email = email;
            return this;
        }

        public ManagerBuilder salary(double salary) {
            this.salary = salary;
            return this;
        }

        public Manager build() {
            return new Manager(name, surname, phone, email, salary);
        }
    }

}
