
-- 뷰(VIEW)
-- 뷰 : 가상테이블
-- 사용되는 경우 1) 테이블의 데이터 조회 시 조인이 지속적으로 반복하는 경우
-- 2) 테이블의 특정 데이터의 보안성을 확보

SELECT *
FROM emp;

-- EMP 테이블에 대한 첫번째 뷰 생성
CREATE VIEW EMP_VIEW_1
AS
SELECT EMPNO, ENAME, JOB
FROM emp;

SELECT * FROM emp_view_1;

-- OR REPLACE VIEW 테이블명 : (업데이트기능) 덮어쓰기
CREATE OR REPLACE VIEW EMP_VIEW_2
AS
SELECT EMPNO, ENAME, SAL, COMM
FROM emp
WHERE SAL >= 350;

SELECT * FROM EMP_VIEW_2;
DROP VIEW EMP_VIEW_2;
