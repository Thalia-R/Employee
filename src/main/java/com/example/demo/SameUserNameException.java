package com.example.demo;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class SameUserNameException extends Exception {
}
