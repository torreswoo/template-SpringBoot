package com.torres;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AppApiController {

    @RequestMapping(value="/hello", method = RequestMethod.GET)
    public String hello(){

        log.info("controller");
        return "hello";
    }

}
