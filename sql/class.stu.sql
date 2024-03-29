-- 학급정보
CREATE TABLE CLASS_INFO(
	CLASS_CODE INT AUTO_INCREMENT PRIMARY KEY
	, CLASS_NAME VARCHAR(20) NOT NULL
);

INSERT INTO CLASS_INFO VALUES (1, '자바반');
INSERT INTO CLASS_INFO VALUES (2, '디자인반');
INSERT INTO CLASS_INFO VALUES (3, '캐드반');

SELECT * FROM ;

-- 학생정보
CREATE TABLE STUDENT_INFO(
	STU_NUM INT AUTO_INCREMENT PRIMARY KEY
	, STU_NAME VARCHAR(20) NOT NULL
	, CLASS_CODE INT REFERENCES CLASS_INFO (CLASS_CODE)
);

INSERT INTO STUDENT_INFO VALUES (1, '김자바', 1);
INSERT INTO STUDENT_INFO VALUES (2, '이자바', 1);
INSERT INTO STUDENT_INFO VALUES (3, '박자바', 1);
INSERT INTO STUDENT_INFO VALUES (4, '최자바', 1);
INSERT INTO STUDENT_INFO VALUES (5, '정자바', 1);

INSERT INTO STUDENT_INFO VALUES (6, '김디자인', 2);
INSERT INTO STUDENT_INFO VALUES (7, '이디자인', 2);
INSERT INTO STUDENT_INFO VALUES (8, '박디자인', 2);
INSERT INTO STUDENT_INFO VALUES (9, '최디자인', 2);
INSERT INTO STUDENT_INFO VALUES (10, '정디자인', 2);

INSERT INTO STUDENT_INFO VALUES (11, '김캐드', 3);
INSERT INTO STUDENT_INFO VALUES (12, '이캐드', 3);
INSERT INTO STUDENT_INFO VALUES (13, '박캐드', 3);
INSERT INTO STUDENT_INFO VALUES (14, '최캐드', 3);
INSERT INTO STUDENT_INFO VALUES (15, '정캐드', 3);

SELECT * FROM STUDENT_INFO;

COMMIT;

SELECT * FROM board;
SELECT * FROM board_member;

-- 모든게시글 게시글 번호, 제목, 작성자명, 작성자 ID를 조회
SELECT BOARD_NUM
	, TITLE
	, MEMBER_NAME
	, MEMBER_ID
FROM board INNER JOIN board_member
ON WRITER = MEMBER_ID;

-- 모든 학생의 학번, 소속 반이름, 학생명 조회
SELECT STU_NUM
	, STU_NAME
	, CLASS_NAME
FROM class_info C INNER JOIN student_info S
ON C.CLASS_CODE = S.CLASS_CODE;

CREATE TABLE STU_SCORE (
	SCORE_NUM INT AUTO_INCREMENT PRIMARY KEY
	, KOR_SCORE INT DEFAULT 0
	, ENG_SCORE INT DEFAULT 0
	, MATH_SCORE INT DEFAULT 0
	, STU_NUM INT REFERENCES student_info (STU_NUM)
);

SELECT KOR_SCORE, ENG_SCORE, MATH_SCORE
FROM stu_score 
WHERE STU_NUM = 1;

SELECT *
FROM stu_score;

INSERT INTO BOARD (
            TITLE
            , CONTENT
            , WRITER
        ) VALUES (
            '등록'
            , '테스트'
            , 'java'
        );

COMMIT;        

SELECT *
FROM board;

SELECT COUNT(*) FROM board;
SELECT COUNT(*) FROM emp;
SELECT COUNT(EMPNO) FROM emp;

SELECT*
FROM board
ORDER BY BOARD_NUM
LIMIT 10 OFFSET 5;

