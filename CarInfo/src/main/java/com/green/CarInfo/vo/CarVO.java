package com.green.CarInfo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class CarVO {
    private int modelNum;
    private String modelName;
    private int price;
    private String manufacturer;

}
