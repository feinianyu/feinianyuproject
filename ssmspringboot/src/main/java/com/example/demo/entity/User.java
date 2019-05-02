package com.example.demo.entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
//@Data
public class User {
    //@Setter @Getter
    private String username;
    //@Setter @Getter
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
