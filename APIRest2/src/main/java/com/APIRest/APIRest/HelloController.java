package com.APIRest.APIRest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HelloController {

    @GetMapping
    public String Hello() {

        return "HOLA MUNDO ALFREDO VIERA";
    }

}
