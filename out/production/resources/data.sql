INSERT INTO user_action_log(id, action_time, user_id) VALUES(1, '2018-01-03 10:11:37', 10021); -- 10021이 계좌개셜
INSERT INTO user_action_log(id, action_time, user_id) VALUES(2, '2018-01-04 10:11:37', 10022); -- 10022이 계좌개셜
INSERT INTO user_action_log(id, action_time, user_id) VALUES(3, '2018-01-04 11:12:50', 10023); -- 10023이 계좌개셜
INSERT INTO user_action_log(id, action_time, user_id) VALUES(4, '2018-01-06 10:11:37', 10024); -- 10024이 계좌개셜
INSERT INTO user_action_log(id, action_time, user_id) VALUES(5, '2018-01-06 18:12:50', 10023); -- 10023이 100,000원을 카카오계좌로 충전
INSERT INTO user_action_log(id, action_time, user_id) VALUES(6, '2018-01-07 11:12:50', 10023); -- 10023가 -> 10021에게 20,000원을 송금 / 받기
INSERT INTO user_action_log(id, action_time, user_id) VALUES(7, '2018-01-07 12:12:50', 10024); -- 10024가 200,000원을 카카오계좌로 충전


-- 계좌계설 --------------------------------------------------------------------
INSERT INTO service_account_log(user_action_log_id, account_number) VALUES (1, '100-111-000');
INSERT INTO service_account_log(user_action_log_id, account_number) VALUES (2, '200-111-000');
INSERT INTO service_account_log(user_action_log_id, account_number) VALUES (3, '300-111-000');
INSERT INTO service_account_log(user_action_log_id, account_number) VALUES (4, '400-111-000');

