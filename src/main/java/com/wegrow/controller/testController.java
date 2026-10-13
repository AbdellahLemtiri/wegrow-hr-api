package com.wegrow.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/patients")
@RestController
public class testController {

    @GetMapping("/helo")
    public String test()
    {
        return "helo";
    }

}
