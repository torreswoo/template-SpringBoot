package com.torres.repository;


import com.torres.entity.UserActionLog;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

public class UserActionLogRepositoryImpl
    extends QueryDslRepositorySupport
    implements UserActionLogRepositoryCustom {

    public UserActionLogRepositoryImpl(){
        super(UserActionLog.class);
    }
}
