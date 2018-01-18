package com.torres.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.torres.entity.UserActionLog;
import java.util.List;

public interface UserActionLogRepository
    extends JpaRepository<UserActionLog, Integer>, UserActionLogRepositoryCustom {

    List<UserActionLog> findAllByUserId(Long user_id);
}
