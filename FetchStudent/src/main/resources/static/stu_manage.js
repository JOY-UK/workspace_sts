
function goSelect(){
    const classCode= document.querySelector('#class-selector').value;

    location.href = `/?classCode=${classCode}`;
}

function fetchSelect(){

    const classCode= document.querySelector('#class-selector').value;


    fetch('/fetchSelect', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
           classCode : classCode
        })
    })
    .then((response) => {
        if(!response.ok){
            alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
            return ;
        }
    
        // return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
        return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        console.log(data);
        // 1. 기존테이블 내용 삭제
        //  1-1). tbody 태그를 선택
        const tbodyTag = document.querySelector('#stu-list-table > tbody');
        
        //  1-2). tbody 태그 안의 모든 내용을 삭제
        tbodyTag.innerHTML = ''; // innerHTML - 내용을 지우는 문법임
        
        //  1-3). 새로 조회한 데이터로 tbody 안의 내용을 채워 줌.
        
        // for (let i = 0; i < data.length; i++){
        //         let str = `<tr>
        //                         <td>${i+1}</td>
        //                         <td>${data[i].className}</td>
        //                         <td>${data[i].stuNum}</td>
        //                         <td>${data[i].stuName}</td>
        //                    </tr>`;
        //     tbodyTag.insertAdjacentHTML('afterbegin', str);
        // }

         let str = ``;
        // for(const stu of data){
        //     str += `<tr>
        //                 <td>${}</td>
        //                 <td>${stu.className}</td>
        //                 <td>${stu.stuNum}</td>
        //                 <td>${stu.stuName}</td>
        //             </tr>`;
        // }

        data.forEach(function(stu, i){
                    str += `<tr>
                        <td>${i}</td>
                        <td>${stu.className}</td>
                        <td>${stu.stuNum}</td>
                        <td onclick="goDetail(${stu.stuNum})">${stu.stuName}</td>
                </tr>`;
        });
        
        tbodyTag.insertAdjacentHTML('afterbegin', str);


    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
    
}

function goDetail(stuNum){
    fetch('/selectStu', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
           stuNum : stuNum
        })
    })
    .then((response) => {
        if(!response.ok){
            alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
            return ;
        }
    
        // return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
        return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        console.log(data);
        //조회된 데이터 그림그리기
        
        const detail_div = document.querySelector('.stu-detail-div');
            
        detail_div.innerHTML=``

        let str = `                                
        <div>
            <div>학생 기본 정보</div>
                <div>
                    <table>
                        <thead>
                            <tr>
                                <td>학번</td>
                                <td>소속반</td>
                                <td>핵생명</td>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td class='stuNumTd'>${data.stuVO.stuNum}</td>
                                <td>${data.stuVO.className}</td>
                                <td>${data.stuVO.stuName}</td>
                            </tr>
                        </tbody>
                    </table >
                </div>
             </div>
        <div>
        <div>학생 점수 정보</div>
            <div>
                <table>
                    <thead>
                        <tr>
                            <td>국어점수</td>
                            <td>영어점수</td>
                            <td>수학점수</td>
                            <td>평균</td>
                        </tr>
                    </thead>
                    
                    <tbody>
                        <tr>
                            <td class="scoreTd">${data.scoreVO == null ? 0 : data.scoreVO.korScore}</td>
                            <td class="scoreTd">${data.scoreVO == null ? 0 : data.scoreVO.engScore}</td>
                            <td class="scoreTd">${data.scoreVO == null ? 0 : data.scoreVO.mathScore}</td>
                            <td>${data.scoreVO == null ? 0.0 : (data.scoreVO.korScore + data.scoreVO.engScore + data.scoreVO.mathScore) / 3.0}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div>
                <input type="button" id="btn" value="점수입력" onclick="goScore()">
            </div>
        </div> 
        `;
        
        detail_div.insertAdjacentHTML('afterbegin', str)
    
    
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
    
}



function goScore(){
    const btn = document.querySelector('#btn');
   
    if(btn.value == '점수입력'){
        const tds = document.querySelectorAll('.scoreTd');
        
        tds.forEach(function(e, i){
            e.innerHTML = '<input type="text" class="scoreInput">';
        });
    
        document.querySelector('#btn').value = '저장';
    }
    else if(btn.value == '저장'){
       const result = confirm('입력한 정보로 점수를 등록할까요?');
       if(result){
           insertScore();
       } 
    }
}

function insertScore(){
        const inputs = document.querySelectorAll('.scoreInput')
        const stuNum = document.querySelector('.stuNumTd').textContent; //textContent 입력한 값 가져오기

    fetch('/insertScore', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
            'korScore'  : inputs[0].value,
            'engScore'  : inputs[1].value,
            'mathScore' : inputs[2].value,
            'stuNum'    : stuNum
        })
    })
    .then((response) => {
        if(!response.ok){
            alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
            return ;
        }
    
        return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
        //return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        alert('점수가 등록되었습니다.');
        goDetail(stuNum);
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
    
}   

    // 반복문 3가지 방법

    // for(let i = 0; i < tds.length; i++){
    //     tds[i].innerHTML = '<input type="text">';
    // }

    // for(const e of tds){
    //     e.innerHTML = '<input type="text">';
    // }



    

