package com.example.repairagencyspring.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = NullPointerException.class)
    public String enityDoesNotExists(Model theModel) {
        theModel.addAttribute("err", "NullPointerException");
        return "Entity does not exists";
    }

    @ExceptionHandler(value = StringIndexOutOfBoundsException.class)
    public String smallValue(Model model){
        model.addAttribute("err", "StringIndexOutOfBoundsException");
        return "Invalid size of the string";
    }

    @ExceptionHandler(value = Exception.class)
    public String AnyOtherHandler() {
        return "error ";
    }
}
