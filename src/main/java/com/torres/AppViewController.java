package com.torres;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AppViewController {

    // intro
    // (Thymeleaf)
    @RequestMapping(value="/intro", method = RequestMethod.GET)
    public String intro(Model model){

        model.addAttribute("title", "torres");
        return "intro";
    }
}
