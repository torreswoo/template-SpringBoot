package com.torres.repository;

import com.torres.entity.*;
import com.querydsl.jpa.JPQLQuery;
import com.torres.entity.UserActionLog;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import java.util.Date;
import java.util.List;

public class UserActionLogRepositoryImpl
    extends QueryDslRepositorySupport
    implements UserActionLogRepositoryCustom {

    public UserActionLogRepositoryImpl(){
        super(UserActionLog.class);
    }

    @Override
    public List<UserActionLog> test(Long userId, Date testTime) throws Exception{

        QUserActionLog qUserActionLog = QUserActionLog.userActionLog;

        QServiceAccountLog qServiceAccountLog = QServiceAccountLog.serviceAccountLog;

        JPQLQuery query =
            from(qUserActionLog)
                .leftJoin(qUserActionLog.serviceAccountLogs, qServiceAccountLog)
                .fetchJoin()
                .where(qUserActionLog.userId.eq(userId));

        return query.fetch();

    }
}
