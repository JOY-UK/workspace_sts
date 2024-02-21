package com.green.shop.item.controller;

import com.green.shop.item.service.ItemServiceImpl;
import com.green.shop.item.vo.ItemVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//상품과 관련된 모든 페이지 이동 처리 컨트롤러
@Controller
@RequestMapping("/item")
public class ItemController {
    @Resource(name = "itemService")
    private ItemServiceImpl itemService;

    //상품 목록 페이지
    @GetMapping("/list")
    public String list(Model model){
        //상품 목록 조회
        List<ItemVO> itemList = itemService.selectItemList();
        model.addAttribute("itemList", itemList);
        return "content/item/item_list";
    }

    //상품 상세 정보 페이지
    @GetMapping("/itemDetail")
    public String itemDetail(@RequestParam(name = "itemCode") int itemCode, Model model){
        //상세 정보 조회
        ItemVO vo = itemService.selectItemDetail(itemCode);
        System.out.println(vo);
        model.addAttribute("item", vo);

        return "content/item/item_detail";
    }


}







