package com.nixsolutions.service.impl;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.sql.Date;
import java.util.Objects;

@XmlRootElement
@Entity
@Table(name = "USER")
@XmlAccessorType(XmlAccessType.FIELD)
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
    @XmlJavaTypeAdapter(SqlDateAdapter.class)
    private Date birthday;

    @Column(name = "role_id")
    private Long roleId;

    public User() {
    }

    public User(String login, String password, String email, String firstName,
                String lastName, Date birthday, Long roleId) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.roleId = roleId;
    }

//    public String getPasswordagain() {
//        return passwordagain;
//    }
//
//    public void setPasswordagain(String passwordagain) {
//        this.passwordagain = passwordagain;
//    }

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

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", login='" + login + '\''
                + ", password='" + password + '\'' + ", email='" + email + '\''
                + ", firstName='" + firstName + '\'' + ", lastName='" + lastName
                + '\'' + ", birthday=" + birthday + ", role=" + roleId + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(login, user.login)
                && Objects.equals(password, user.password) && Objects
                .equals(email, user.email) && Objects
                .equals(firstName, user.firstName) && Objects
                .equals(lastName, user.lastName) && Objects
                .equals(birthday, user.birthday) && Objects
                .equals(roleId, user.roleId);
    }

    static class SqlDateAdapter extends XmlAdapter<java.util.Date, Date> {

        @Override
        public java.util.Date marshal(java.sql.Date sqlDate) throws Exception {
            if (null == sqlDate) {
                return null;
            }
            return new java.util.Date(sqlDate.getTime());
        }

        @Override
        public java.sql.Date unmarshal(java.util.Date utilDate) throws Exception {
            if (null == utilDate) {
                return null;
            }
            return new java.sql.Date(utilDate.getTime());
        }

    }

}
