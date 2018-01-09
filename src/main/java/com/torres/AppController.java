package com.torres;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AppController {

    @RequestMapping("/intro")
    public ModelAndView intro(){
        Map model = new HashMap();
        model.put("title", "Torres");
        return new ModelAndView("intro", model);
    }

}
