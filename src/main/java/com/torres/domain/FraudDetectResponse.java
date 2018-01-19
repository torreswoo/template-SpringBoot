package com.torres.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FraudDetectResponse {

    Long user_id;
    Boolean is_fraud;
    String rule;
}
