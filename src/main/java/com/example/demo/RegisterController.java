package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class RegisterController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    TempUser tempUser;




    @GetMapping("/register-form")
    public String signup(Model model, @ModelAttribute User user) {
        model.addAttribute("tempRegisterUser", user);
        return "register-form";
    }

    @PostMapping("/register-form")
    public String postSignup(@Valid User user, BindingResult result, @RequestParam String password2, Model model) throws SameUserNameException, WrongPasswordException {
        boolean isInDatabase = userRepository.existsUserByUsername(user.getUsername());

        if (isInDatabase) {
            tempUser.addNewUser(user);
            model.addAttribute("user", user);
            throw new SameUserNameException();
        }

        UserValidator userValidator = new UserValidator();
        if (userValidator.supports(user.getClass())) {
            userValidator.validate(user, result);
        }
        if (!user.getPassword().equals(password2)) {
            tempUser.addNewUser(user);
            model.addAttribute("user", user);
            throw new WrongPasswordException();
        }


        if (result.hasErrors()) {
//            model.addAttribute("error", "Failed!");
            return "register-form";
        }

        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);

        return "login";
    }

    @ExceptionHandler(SameUserNameException.class)
    String invalidUsername(Model model) {
        model.addAttribute("user", tempUser.getTempararyUser());
        model.addAttribute("invalidUserName", String.format("User %s already exists", tempUser.getTempararyUser().getUsername()));
        tempUser.removeTempararyUser();
        return "register-form";
    }

    @ExceptionHandler(WrongPasswordException.class)
    String confirmedPassword(Model model) {
        model.addAttribute("user", tempUser.getTempararyUser());
        model.addAttribute("invalidPassword", "Please enter a valid password - Password doesn't match");
        tempUser.removeTempararyUser();
        return "register-form";
    }

}
