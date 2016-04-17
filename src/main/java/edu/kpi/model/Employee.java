package edu.kpi.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class Employee implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true)
    private String phone;

    @Column(unique = true)
    private String passportId;


    public Employee() {
    }

    public Employee(EmployeeBuilder builder) {
        this.firstName = builder.fName;
        this.lastName = builder.lName;
        this.phone = builder.phone;
        this.passportId = builder.passportId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    // TODO Builder Pattern
    public static class EmployeeBuilder {
        private String fName;
        private String lName;
        private String phone;
        private String passportId;

        public EmployeeBuilder setFName(String fName) {
            this.fName = fName;
            return this;
        }

        public EmployeeBuilder setLName(String lName) {
            this.lName = lName;
            return this;
        }

        public EmployeeBuilder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public EmployeeBuilder setPassportId(String passportId) {
            this.passportId = passportId;
            return this;
        }

        public Employee build() {
            return new Employee(this);
        }
    }

}
