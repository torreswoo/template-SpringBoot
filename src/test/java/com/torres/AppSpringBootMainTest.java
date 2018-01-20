package com.torres;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AppSpringBootMainTest {

    @Autowired
    private AppSpringBootInterface appMain;

    @Before
    public void setup() throws Exception {
        appMain.start();
    }

    @Test
    public void contextLoads() {
    }

}