package com.torres.service;


import com.torres.entity.UserActionLog;
import com.torres.repository.UserActionLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CreatingUserActionLogService {
    @Autowired
    private UserActionLogRepository userActionLogRepository;

    // use
    public List<UserActionLog> findAllUserActionLogByUserId(Long userId){

        return userActionLogRepository.findAllByUserId(userId);
    }

    // use QueryDSL
    public List<UserActionLog> test(Long userId, Date testTime) throws Exception{
        List<UserActionLog> userActionLogList = userActionLogRepository.test(userId, testTime);
        return userActionLogList;
    }

}
