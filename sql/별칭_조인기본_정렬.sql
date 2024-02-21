-- 별칭사용(조회시 컬럼명을 변경)
SELECT EMP.EMPNO AS 사원
	, ENAME AS NM 
	, SAL 급여
FROM emp;

SELECT E.EMPNO
	, ENAME
	, SAL
FROM emp E;


-- 컬럼명을 조회 할때는 테이블명,컬럼명으로 조회 통상적으로 테이블명 생략
SELECT EMP.EMPNO
	, ENAME
	, SAL
FROM emp;

-- join
SELECT * FROM emp;
SELECT * FROM dept;

-- 사원의 사번, 이름, 부서명을 조회
-- 1. CROSS JOIN(공부를 위해 학습, 실무에서 사용X)
SELECT EMPNO, ENAME, DNAME
FROM emp CROSS JOIN dept;

-- 2. INNER JOIN(교집합)
-- ON : 조인하는 두 테이블간 공통적으로 지니는 컬럼의 값이 같다라는 조건
SELECT EMPNO, ENAME, DNAME
FROM emp INNER JOIN dept
ON emp.DEPTNO = dept.DEPTNO;

SELECT EMPNO, ENAME, DNAME, D.DEPTNO -- DEPTNO(중복값)조회 하고싶을때 테이블명 넣어줌.
FROM emp E INNER JOIN dept D
ON E.DEPTNO = D.DEPTNO;

-- 데이터 조회 시 정렬하여 출력
-- 사원의 모든 정보를 조회하고, 급여를 조회(낮은순 오름차순 ASC(생략가능), 내림차순 DESC)
SELECT *
FROM emp
ORDER BY SAL DESC;

-- 사원의 모든 데이터를 조회 및 급여 기준 내림차순 정렬, 급여가 같다면 사번기준 오름차순 정렬
SELECT * 
FROM emp
ORDER BY SAL DESC, EMPNO;

-- 급여가 200 이상이면서 커미션은 NULL이 아니고 사원의 사번, 이름, 급여, 부서번호, 부서명 조회
-- 단, 부서번호 기준 오름 차순 정렬 후 부서번호가 같다면 급여 기준 내림차순으로 정렬
-- 사번은 '사원번호'라는 별칭을 사용해서 조회
SELECT EMPNO 사원번호
	, ENAME
	, SAL
	, E.DEPTNO
	, DNAME
FROM emp E INNER JOIN dept D
ON E.DEPTNO = D.DEPTNO
WHERE SAL >= 200 AND COMM IS NOT NULL
ORDER BY E.DEPTNO, SAL DESC;
;
