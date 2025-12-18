-- course
INSERT INTO course (id, title, creator_id, created_date, updated_date)
VALUES (1, 'Java LMS - 기본', 1, CURRENT_TIMESTAMP(), NULL);

INSERT INTO course (id, title, creator_id, created_date, updated_date)
VALUES (2, 'Java LMS - 심화', 2, CURRENT_TIMESTAMP(), NULL);

INSERT INTO course (id, title, creator_id, created_date, updated_date)
VALUES (3, 'Java LMS - 프로젝트', 1, CURRENT_TIMESTAMP(), NULL);

-- session
INSERT INTO session (id, course_id, creator_id, title, contents, start_date, end_date, cover_image_size, cover_image_type, dimensions_width, dimensions_height, dimensions_ratio, created_date, updated_date)
VALUES (1, 1, 1, 'TDD 기반 Java LMS', '테스트 주도 개발로 LMS를 만들어봅니다.', DATE '2099-01-10', DATE '2099-01-31', 1500000, 'JPEG', 300, 200, 1.5, CURRENT_TIMESTAMP(), NULL);

INSERT INTO session (id, course_id, creator_id, title, contents, start_date, end_date, cover_image_size, cover_image_type, dimensions_width, dimensions_height, dimensions_ratio, created_date, updated_date)
VALUES (2, 1, 2, 'Spring JDBC 심화', 'JdbcTemplate 기반으로 Repository를 구현합니다.', DATE '2099-02-01', DATE '2099-02-12', 2000000, 'PNG', 450, 300, 1.5, CURRENT_TIMESTAMP(), NULL);

INSERT INTO session (id, course_id, creator_id, title, contents, start_date, end_date, cover_image_size, cover_image_type, dimensions_width, dimensions_height, dimensions_ratio, created_date, updated_date)
VALUES (3, 2, 1, '도메인 모델링과 제약', '객체 제약조건(날짜, 이미지 크기/비율)을 만족하는 모델을 설계합니다.', DATE '2099-03-01', DATE '2099-03-25', 1200000, 'SVG', 600, 400, 1.5, CURRENT_TIMESTAMP(), NULL);

INSERT INTO session (id, course_id, creator_id, title, contents, start_date, end_date, cover_image_size, cover_image_type, dimensions_width, dimensions_height, dimensions_ratio, created_date, updated_date)
VALUES (4, 2, 2, 'Java LMS - 심화 실습', 'course_id=2에 대한 추가 세션 테스트 데이터입니다.', DATE '2099-04-01', DATE '2099-04-05', 1800000, 'JPG', 750, 500, 1.5, CURRENT_TIMESTAMP(), NULL);

-- enrollment
INSERT INTO enrollment (id, session_id, type, tuition_fee, max_enrollment, session_status, created_date, updated_date)
VALUES (1, 1, 'PAID', 10000, 30, 'RECRUITING', CURRENT_TIMESTAMP(), NULL);

INSERT INTO enrollment (id, session_id, type, tuition_fee, max_enrollment, session_status, created_date, updated_date)
VALUES (2, 2, 'FREE', 0, 0, 'RECRUITING', CURRENT_TIMESTAMP(), NULL);

INSERT INTO enrollment (id, session_id, type, tuition_fee, max_enrollment, session_status, created_date, updated_date)
VALUES (3, 3, 'PAID', 5000, 20, 'RECRUITING', CURRENT_TIMESTAMP(), NULL);

INSERT INTO enrollment (id, session_id, type, tuition_fee, max_enrollment, session_status, created_date, updated_date)
VALUES (4, 4, 'FREE', 0, 0, 'RECRUITING', CURRENT_TIMESTAMP(), NULL);

-- enrolled_user
INSERT INTO enrolled_user (id, enrollment_id, created_date, updated_date)
VALUES (1, 1, CURRENT_TIMESTAMP(), NULL),
       (2, 1, CURRENT_TIMESTAMP(), NULL),
       (3, 2, CURRENT_TIMESTAMP(), NULL),
       (4, 3, CURRENT_TIMESTAMP(), NULL),
       (5, 3, CURRENT_TIMESTAMP(), NULL),
       (6, 4, CURRENT_TIMESTAMP(), NULL),
       (7, 1, CURRENT_TIMESTAMP(), NULL),
       (8, 1, CURRENT_TIMESTAMP(), NULL),
       (9, 1, CURRENT_TIMESTAMP(), NULL),
       (10, 1, CURRENT_TIMESTAMP(), NULL),
       (11, 1, CURRENT_TIMESTAMP(), NULL),
       (12, 1, CURRENT_TIMESTAMP(), NULL),
       (13, 1, CURRENT_TIMESTAMP(), NULL),
       (14, 1, CURRENT_TIMESTAMP(), NULL),
       (15, 1, CURRENT_TIMESTAMP(), NULL),
       (16, 1, CURRENT_TIMESTAMP(), NULL),
       (17, 2, CURRENT_TIMESTAMP(), NULL),
       (18, 2, CURRENT_TIMESTAMP(), NULL),
       (19, 2, CURRENT_TIMESTAMP(), NULL),
       (20, 3, CURRENT_TIMESTAMP(), NULL),
       (21, 3, CURRENT_TIMESTAMP(), NULL),
       (22, 3, CURRENT_TIMESTAMP(), NULL),
       (23, 3, CURRENT_TIMESTAMP(), NULL),
       (24, 3, CURRENT_TIMESTAMP(), NULL),
       (25, 3, CURRENT_TIMESTAMP(), NULL),
       (26, 3, CURRENT_TIMESTAMP(), NULL),
       (27, 3, CURRENT_TIMESTAMP(), NULL),
       (28, 3, CURRENT_TIMESTAMP(), NULL),
       (29, 4, CURRENT_TIMESTAMP(), NULL),
       (30, 4, CURRENT_TIMESTAMP(), NULL),
       (31, 4, CURRENT_TIMESTAMP(), NULL),
       (32, 4, CURRENT_TIMESTAMP(), NULL),
       (33, 4, CURRENT_TIMESTAMP(), NULL),
       (34, 4, CURRENT_TIMESTAMP(), NULL),
       (35, 4, CURRENT_TIMESTAMP(), NULL);