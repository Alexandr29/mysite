package com.nixsolutions.service.impl;

import java.sql.Date;

public class User {
    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    private Long id;
    private String login;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private Date birthday;
    private Long role_id;
    private int age;

    public User(Long id, String login, String password, String email,
            String firstName, String lastName, Date date, Long role_id) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = date;
        this.role_id = role_id;
        this.age = getAge();

    }

    public User(String login, String password, String email, String firstName,
            String lastName,Date date, Long role_id) {
        this(null,login,password,email,firstName,lastName,date, role_id);


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
        getAge();
    }
    public Long getRole_id() {
        return role_id;
    }
    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }
    public int getAge() {
        this.age = calculateDifferenceInYears(birthday.getYear(), new java.util.Date().getYear()); // new Date() = today.
        return this.age;
    }
    private int calculateDifferenceInYears(int birthday,int year){
        int res = year-birthday;
    return res;
    }

    @Override public String toString() {
        return  '\n'+"User{" + "id=" + id + ", login='" + login + '\''
                + ", password='" + password + '\'' + ", email='" + email + '\''
                + ", firstName='" + firstName + '\'' + ", lastName='" + lastName
                + '\'' + ", birthday=" + birthday + ", role_id=" + role_id
                + '}';
    }
}
