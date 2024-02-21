package com.green.shop.study.fetch.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString
public class MapDataVO {
    private int cartCode;
    private MapDataMember memberInfo;
    private itemInfo itemInfo;
}
@Getter
@Setter
@ToString
class MapDataMember{
    private String memberId;
    private String memberName;
}
@Getter
@Setter
@ToString
class itemInfo{
    private int itemCode;
    private String itemName;
    private int itemPrice;
    private List<ImgInfo> imgList;
}
@Getter
@Setter
@ToString
class ImgInfo{
    private String imgName;
    private int imgCode;
}