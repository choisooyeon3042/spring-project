package com.estsoft.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.Arrays;

@Controller
public class PageController {
    @GetMapping("/thymeleaf/example")
    public String thymeleafPage(Model model) {
        Person person = new Person();
        person.setId(1L);
        person.setName("홍홍홍");
        person.setAge(35);
        person.setHobbies(Arrays.asList("드라이브", "운동"));

        model.addAttribute("person", person);
        model.addAttribute("today", LocalDateTime.now());

        return "examplePage";
    }
}
