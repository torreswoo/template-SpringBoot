package com.torres.app;

import com.torres.domain.FraudDetectResponse;
import com.torres.entity.UserActionLog;
import com.torres.service.CreatingUserActionLogService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;
import java.util.List;

@RestController
@Slf4j
public class AppController {

    // Config - Swagger
    @ApiOperation(value = "Swagger UI main", hidden= true)
    @RequestMapping(value="/", method = RequestMethod.GET)
    public RedirectView swaggerRedirect(){
        return new RedirectView("/swagger-ui.html");
    }

    // intro
    // (Thymeleaf)
    @RequestMapping(value="/intro", method = RequestMethod.GET)
    public String intro(Model model){

        model.addAttribute("title", "torres");
        return "intro";
    }

    // API test
    @RequestMapping(value="/hello", method = RequestMethod.GET)
    public String hello(){

        log.info("controller");
        return "hello";
    }

}
