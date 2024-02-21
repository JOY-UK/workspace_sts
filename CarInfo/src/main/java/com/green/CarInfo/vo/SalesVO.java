package com.green.CarInfo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class SalesVO {
    private int saleNum;
    private String purchaser;
    private String purchaserTel;
    private String color;
    private String saleDate;
    private int modelNum;
    private CarVO carList;
}
