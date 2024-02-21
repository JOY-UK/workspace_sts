const updateItemCode = document.querySelector('#updateItemCode').value;
if(updateItemCode != 0){
    itemInfo(updateItemCode);
}

//상품 목록 테이블의 행 클릭시 
function itemInfo(itemCode){
    fetch('/admin/updateItem', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
            'itemCode' : itemCode
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
        console.log(data.itemDetail);
        
        const detail_div = document.querySelector('.detail-div');
        detail_div.innerHTML = '';

        let str = '';
        str += `
        <form action="/admin/itemInfoUpdate" method="post">
        <input type="hidden" name="itemCode" value="${data.itemDetail.itemCode}">
        <div style="text-align: center;" class="mb-3 mt-3">
                <h3>상품기본정보</h3>
            </div>
            <div class="row">
                <div class="col">
                
                <table class="table table-bordered align-middle text-center">
                    <colgroup>
                        <col width="25%">
                        <col width="35%">
                        <col width="25%">
                        <col width="*">
                    </colgroup>
                    <tr>
                        <td>카테고리</td>
                        <td>
                            <select name="cateCode">`;
                           for(const category of data.cateList){
                            if(category.cateCode == data.itemDetail.cateCode){
                                str += `<option value="${category.cateCode}"selected>${category.cateName}</option>`
                            }else{
                                str += `<option value="${category.cateCode}">${category.cateName}</option>`
                            }
                           }     
        str +=             `</select>
                        </td>
                        <td>상품수량</td>
                        <td><input type="text" name="itemStock" style="width: 70%;" value="${data.itemDetail.itemStock}"></td>
                    </tr>
                    <tr>
                        <td>상품명</td>
                        <td colspan="3">
                           <input type="text" name="itemName" style="text-center" value="${data.itemDetail.itemName}"> 
                        </td>
                    </tr>
                    <tr>
                        <td>상품상태</td>
                        <td colspan="3">`
                           if(data.itemDetail.itemStataus == 1){
                            str += `<input type="radio" checked name="itemStataus" value="1">준비중`
                           }else{
                            str += `<input type="radio" name="itemStataus" value="1">준비중`
                           }
                           if(data.itemDetail.itemStataus == 2){
                            str += `<input type="radio"checked name="itemStataus" value="2">판매중`
                           }else{
                            str +=`<input type="radio" name="itemStataus" value="2">판매중`
                           }
                           if(data.itemDetail.itemStataus == 3){
                            str += `<input type="radio" checked name="itemStataus" value="3">매진`
                           }else{
                            str += `<input type="radio" name="itemStataus" value="3">매진`
                           }

                   
        str +=     `</td>
                    </tr>
                </table>
                </div>
            </div>
            <div style="text-align: center;" class="mb-3 mt-3">
                <h4>상품이미지정보</h4>
            </div>
            <div class="row">
                <div class="col">
                    <table class="table table-bordered align-middle text-center">
                        <colgroup>
                            <col witdth="20%">
                            <col witdth="30%">
                            <col witdth="20%">
                            <col witdth="30%">
                        </colgroup>
                        <tr>
                            <td>메인이미지</td>`
                            for(const img of data.itemDetail.imgList){
                                if(img.isMain == 'Y'){
                                    str += `<td><span onclick="imgModal('${img.attachedFileName}')">${img.originFileName}</span></td>`;
                                }
                            }
            str +=      `</tr>
                        <tr>
                            <td>상세이미지</td>`
                            for(const img of data.itemDetail.imgList){
                                if(img.isMain == 'N'){
                                    str += `<td><span onclick="imgModal('${img.attachedFileName}')">${img.originFileName}</span></td>`;
                                }
                            }
            str +=      `</tr>


                        </tr>
                    </table>
                    <span><input type="submit" value="변경" class="btn btn-primary "></span>
                    
                </div>
            </div>
            </form>
        `;
        detail_div.insertAdjacentHTML('afterbegin', str)

        
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
    
}



function imgModal(attachedFileName){
    const imgModal = new bootstrap.Modal('#img-modal');
    const img_tag = document.querySelector('#img-modal img')
    img_tag.src = `/upload/${attachedFileName}`;
    

    imgModal.show();

}