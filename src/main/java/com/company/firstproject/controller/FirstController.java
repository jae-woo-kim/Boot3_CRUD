package com.company.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi")
    public String niceToMeetYou (Model model) {
        model.addAttribute("username", "홍길동");
        return "greetings";
    }
    @GetMapping("/bye")
    public void bye (Model model) {
        model.addAttribute("username", "홍길동");
    }
}
