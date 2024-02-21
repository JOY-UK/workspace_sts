package com.green.shop.cart.controller;

import com.green.shop.cart.service.CartService;
import com.green.shop.cart.service.CartServiceImpl;
import com.green.shop.cart.vo.CartVO;
import com.green.shop.cart.vo.CartViewVO;
import com.green.shop.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Resource(name = "cartService")
    private CartServiceImpl cartService;

    //장바구니에 상품 등록
    @ResponseBody
    @PostMapping("/insertCart")
    public void insertCart(CartVO cartVO, HttpSession session){
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
        cartVO.setMemberId(loginInfo.getMemberId());
        cartService.insertCart(cartVO);
    }

    //장바구니 목록 조회
    @GetMapping("/list")
    public String list(HttpSession session, Model model, @RequestParam(name = "page", required = false, defaultValue = "cartList") String page){
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        List<CartViewVO> cartList = cartService.selectCartList(loginInfo.getMemberId());
        model.addAttribute("cartList", cartList);



        //총 가격를 계산한 후 html 전달
        int sum = 0;
        for(CartViewVO e : cartList){
            sum = sum + e.getTotalPrice();
        }
        //model.addAttribute("finalPrice", sum);
        model.addAttribute("page", page);
        return "content/cart/cart_list";
    }

    // 장바구니에서 상품 하나 삭제
    @GetMapping("/deleteCart")
    public String deleteCart(@RequestParam(name = "cartCode") int cartCode){
        cartService.deleteCart(cartCode);
        return "redirect:/cart/list";
    }

    //장바구니에 담긴 상품 수량 변경
    @ResponseBody
    @PostMapping("/changeCnt")
    public void changeCnt(CartVO cartVO){
        cartService.updateCartCnt(cartVO);
    }

    //장바구니 상품 여러개 삭제
    @GetMapping("/deleteCarts")
    public String deleteCarts(CartVO cartVO){
        cartService.deleteCarts(cartVO);

        return "redirect:/cart/list";
    }

}










