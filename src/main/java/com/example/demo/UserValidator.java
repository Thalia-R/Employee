package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {
    @Autowired
    UserRepository userRepository;

    @Override
    public boolean supports(Class aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors e) {

        User user = (User) object;

        if (user.getUsername() == null || user.getUsername().equals("")) {
            e.rejectValue("username", "username.empty");
        }

        if (user.getPassword() == null || user.getPassword().equals("")) {
            e.rejectValue("password", "incorrect");
        }

        if (user.getPassword() == null || user.getPassword().equals("")) {
            e.rejectValue("password", "incorrect");
        }
    }
}
