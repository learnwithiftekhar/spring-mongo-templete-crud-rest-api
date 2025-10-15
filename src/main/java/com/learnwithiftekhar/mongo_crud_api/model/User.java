package com.learnwithiftekhar.mongo_crud_api.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

    @Id
    private String id;

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email")
    @Indexed(unique = true)
    private String email;

    @Min(value = 18, message = "Age should be at least 18")
    @Max(value = 100, message = "Age should not exceed 100")
    private Integer age;

    private String city;

    public User(String name, String email, Integer age, String city) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
