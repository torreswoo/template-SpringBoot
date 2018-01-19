package com.torres;

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

    // API test
    @RequestMapping(value="/hello", method = RequestMethod.GET)
    public String hello(){

        log.info("controller");
        return "hello";
    }

    // API Fraud Detection
    @Autowired
    private CreatingUserActionLogService creatingUserActionLogService;

    @ApiOperation(value = "Fraud Detection API using user_id")
    @RequestMapping(
        value="/v1/fraud/{user_id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success to check fraud detection"),
        @ApiResponse(code = 400, message = "Bad Request - No exist this URL. check the URL again"),
        @ApiResponse(code = 404, message = "Not Found - Not Found. check user_id"),
        @ApiResponse(code = 500, message = "Internal Server Error") })
    public @ResponseBody FraudDetectResponse fraudDetectResponse(@PathVariable Long user_id){

        // TODO: check user_id is LONG?
        log.info("[REQ] {} - start check Fraud Detection.", user_id);

        try {
            // TEST: check all UserAction
            Date testTime = new Date();
            log.info(" --- time : {}", testTime.getTime());
            List<UserActionLog> userActionLogList = creatingUserActionLogService.test(user_id, testTime);
            log.info(" - {}", userActionLogList.size());
            userActionLogList.stream().forEach(log -> System.out.println(log));
        }catch (Exception ex){
            log.error("(SEND EXCEPTION during meta) 처리되지않은 오류 발생 : {}", ex.getMessage());
        }
        //


        FraudDetectResponse fraudDetectResponse = new FraudDetectResponse(user_id, Boolean.TRUE, "RuleA,RuleB");

//        log.info("test {} ", creatingUserActionLogService.test(user_id));
        log.info("[RES] {} - fraud result : {}", user_id, fraudDetectResponse.getIs_fraud());
        return fraudDetectResponse;
    }

}
