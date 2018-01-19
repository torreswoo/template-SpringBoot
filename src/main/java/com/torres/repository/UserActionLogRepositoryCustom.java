package com.torres.repository;

import com.torres.entity.UserActionLog;

import java.util.Date;
import java.util.List;

public interface UserActionLogRepositoryCustom {
    List<UserActionLog> test(Long userId, Date testTime) throws Exception;
}
