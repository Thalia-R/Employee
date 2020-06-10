package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TempUser {
    private List<User> tempUser;

    public TempUser() {
        tempUser = new ArrayList<>();
    }

    public List<User> getAllTempUsers() {
        return tempUser;
    }

    public void addNewUser(User user){

        tempUser.add(user);
    }

    public void removeTempararyUser(){
        tempUser.remove(tempUser.size()-1);
    }

    public User getTempararyUser(){
        return tempUser.get(tempUser.size()-1);
    }
}
