package com.wegrow.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/h")
@RestController
public class testController {

    @GetMapping("/helo")
    public String test()
    {
        return "helo";
    }

}
