package com.torres;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class AppController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "test";
    }

}
