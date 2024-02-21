-- 데이터 조회
-- # 주석

-- 데이터 조회 1
-- SELECT 컬럼명들 FROM 테이블명;
-- 1. EMP테이블에서 모든 사원의 사번,이름,급여 정보조회

SELECT EMPNO, ENAME, SAL FROM emp; 
 
-- 2. 모든 사원의 이름, 직급, 입사일, 부서번호 조회
SELECT ENAME, JOB, HIREDATE, DEPTNO FROM emp;

-- 3. 모든 사원의 모든 정보를 조회
-- *(에스테리스크) : ALL
SELECT * FROM emp;

-- 4. 조건을 통한 조회(급여가 300이상인 사원들의 사번,사원명, 급여 조회)
SELECT EMPNO, ENAME, SAL 
FROM emp 
WHERE SAL >= 600;

-- 5. 직급이 대리인 사원들의 사원명, 직급, 급여를 조회
SELECT ENAME, JOB, SAL
FROM emp
WHERE JOB = '대리';

-- 6. 직급이 과장이고 급여가 400이상인 사원들의 모든 정보를 조회
-- 같지않다 : !=, <>
SELECT *
FROM emp
WHERE JOB = '과장' AND SAL >=400;

-- COMM 컬럼이 NULL인 사원의 모든 정보 조회
SELECT *student
FROM emp
WHERE COMM IS NULL;
-- WHERE COMM IS NOT NULL; NULL이 아닌 데이터 조회

-- 급여가 500미만이거나 700이상이면서 직급은 차장이고 COMM은 NULL인 사원들의 사번, 사원명, 급여, 직급 , 커미션 정보를 조회
SELECT EMPNO, ENAME, SAL, JOB, COMM
FROM emp
WHERE (SAL < 500 OR SAL >= 700) 
AND JOB = '차장' AND COMM IS NULL;

-- 
SELECT *
FROM emp;

-- LIKE 연산자, 와일드카드(%~~~%)
-- 와일드 카드 : % , _
-- % : 
-- _ : 

-- 사원명에서 '이'라는 글자가 포함된 사원 조회
SELECT *
FROM emp
WHERE ENAME LIKE '%이%';

-- 사원명이 세 글자이면서 중간 글자가 '이' 인 사원 조회
SELECT *
FROM emp
WHERE ENAME LIKE '_이_';

-- 사원명이 세번째 글자가 '이' 인 사원 조회
SELECT *
FROM emp
WHERE ENAME LIKE '__이';

-- UPPER()-> 대문자로변경
-- LOWER()-> 소문자로 변경
SELECT 'java', UPPER('java'), LOWER('JAVA');

