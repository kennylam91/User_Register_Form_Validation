package com.codegym.ums.model;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Component
@Entity
@Table(name = "users")
public class User implements Validator {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private Integer age;


    private String email;

    public User() {
    }

    public User(String firstName, String lastName, String phoneNumber, Integer age, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.age = age;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }


    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String phoneNumber = user.getPhoneNumber();
        Integer age = user.getAge();
        String email=user.getEmail();


        ValidationUtils.rejectIfEmpty(errors, "firstName", "firstName.empty");
        ValidationUtils.rejectIfEmpty(errors, "lastName", "lastName.empty");
        if (firstName.length() < 1 || (firstName.length() > 45)) {
            errors.rejectValue("firstName", "firstName.length");
        }
        if (lastName.length() < 1 || lastName.length() > 45) {
            errors.rejectValue("lastName", "lastName.length");
        }
        if (phoneNumber != "") {
            if (phoneNumber.length() < 10 || phoneNumber.length() > 11) {
                errors.rejectValue("phoneNumber", "phoneNumber.length");
            }
            if (!phoneNumber.matches("(^$|[0-9]*$)")) {
                errors.rejectValue("phoneNumber", "phoneNumber.matches");
            }
            if (!phoneNumber.startsWith("0")) {
                errors.rejectValue("phoneNumber", "phoneNumber.startWith");
            }
        }

        if (age != null) {
            if (age < 18) {
                errors.rejectValue("age", "age.compare");
            }
        }

        if(email!=""){
            if(!email.matches("^(.+)@(.+)$")){
                errors.rejectValue("email","email.matches");
            }
        }

    }
}
