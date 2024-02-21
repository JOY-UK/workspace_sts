package com.green.Board2.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class SearchVO extends PageVO{  //extendes(상속) pageVO에 있는 변수 serchVO에서 사용가능
    private String searchType;
    private String searchValue;
}
