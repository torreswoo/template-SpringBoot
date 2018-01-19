package com.torres.repository;

import com.torres.config.DatabaseConfig;
import com.torres.entity.UserActionLog;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@ContextConfiguration(classes = DatabaseConfig.class)
public class UserActionLogRepositoryTest {


    @Autowired
    private UserActionLogRepository userActionLogRepository;

    @Before
    public void setUp() throws Exception{
        assertThat(userActionLogRepository).isNotNull();
    }

    @Test
    public void repository테스트_test() throws Exception{

        // given
        Long userID = new Long(10023);
        Date testTime = new Date();

        // when
        List<UserActionLog> userActionLogs = userActionLogRepository.test(userID, testTime);

        // then
        assertThat(userActionLogs.size()).isEqualTo(3);


    }
}