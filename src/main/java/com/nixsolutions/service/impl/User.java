package com.nixsolutions.service.impl;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @NotBlank
    @Size(min = 4, max = 40, message = "login should be from 4 to 40 symbols")
    @Column(name = "LOGIN")
    private String login;

    @Size(min = 6, max = 50, message = "password should be min. 6 symbols")
    @Column(name = "PASSWORD")
    private String password;

    @Transient
    private String passwordagain;

    @NotBlank
    @Email
    @Column(name = "EMAIL")
    private String email;

    @NotBlank
    @Size(min = 2, max = 30, message = "Enter valid First name")
    @Column(name = "FIRSTNAME")
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 30, message = "Enter valid Last name")
    @Column(name = "LASTNAME")
    private String lastName;

    @Column(name = "date")
    private Date birthday;

    //@ManyToOne(fetch = FetchType.EAGER)
    @Column(name = "role_id")
    private Long role_id;


    //private int age;

    public User() {
    }

//    public User(Long id, String login, String password, String email,
//            String firstName, String lastName, Date birthday, Long role_id) {
//        this.id = id;
//        this.login = login;
//        this.password = password;
//        this.email = email;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.birthday = birthday;
//        this.role_id = role_id;
//    }

    public User(String login, String password, String email, String firstName,
            String lastName, Date birthday, Long role_id) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.role_id = role_id;
    }

    public String getPasswordagain() {
        return passwordagain;
    }

    public void setPasswordagain(String passwordagain) {
        this.passwordagain = passwordagain;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }
   // public int getAge() {
        //this.age = calculateDifferenceInYears(birthday.getYear(), new java.util.Date().getYear()); // new Date() = today.
       // return this.age;
    //}
    private int calculateDifferenceInYears(int birthday,int year){
        int res = year-birthday;
        return res;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", role=" + role_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(birthday, user.birthday) &&
                Objects.equals(role_id, user.role_id);
    }
}
