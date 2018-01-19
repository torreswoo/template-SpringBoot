package com.torres.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "user_action_log")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserActionLog implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    @Getter
    private Integer id;

    @Column(name = "action_time")
    @Getter
    private Date actionTime;

    @Column(name = "user_id")
    @Getter
    private Long userId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userActionLog")
    @Getter
    private Set<ServiceAccountLog> serviceAccountLogs;

}
