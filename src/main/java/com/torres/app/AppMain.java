package com.torres.app;

import com.torres.AppSpringBootInterface;
import com.torres.AppSpringBootMain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@Slf4j
public class AppMain
    extends AppSpringBootMain
    implements AppSpringBootInterface {

//    @Autowired
//    private IpInfoManager ipInfoManager;

    @Override
    public void start() {
//        this.ipInfoManager.updateGeolocationIpInfo();
        log.info("[START] start App server...");
    }

    @Override
    public void stop() {
        log.info("[SHUTDOWN] shutdown App server...");
    }

    public AppMain(){    }

    // AppMain - main()
    public static void main(String[] args) {
        AppSpringBootMain.main(args);
    }



}
