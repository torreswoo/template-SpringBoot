package com.torres.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_action_log")
@NoArgsConstructor
@AllArgsConstructor
public class UserActionLog implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    @Getter
    private Integer id;

    @Column(name = "user_id")
    @Getter
    private Long userId;
}
