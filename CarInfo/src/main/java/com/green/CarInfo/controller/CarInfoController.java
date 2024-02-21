package com.green.CarInfo.controller;

import com.green.CarInfo.service.CarInfoServiceImpl;
import com.green.CarInfo.vo.CarVO;
import com.green.CarInfo.vo.SalesVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/car")
public class CarInfoController {

    @Resource(name = "carInfo")
    private CarInfoServiceImpl carInfoService;

    //메인화면
    @GetMapping("/main")
    public String main(){
        return "content/main";
    }

    //차량관리이동 및 목록조회
    @GetMapping("/carInfo")
    public String carInfo (Model model, CarVO carVO){
        List<CarVO> carList = carInfoService.carSelect(carVO);
        model.addAttribute("carList", carList);
        return "content/carInfo";
    }

    //차량등록
    @PostMapping("/carInsert")
    public String insertCarInfo(CarVO carVO){
        carInfoService.carInsert(carVO);
        return "redirect:/car/carInfo";
    }

    //판매정보페이지 순수이동만
    @GetMapping("/saleInfo")
    public String salesInfo(CarVO carVO, Model model){
        List<CarVO> carList = carInfoService.carSelect(carVO);
        model.addAttribute("carList", carList);
        return "content/saleInfo";
    }

    //판매정보등록
    @PostMapping("/saleInfoInsert")
    public String salesInfo(SalesVO salesVO){
        carInfoService.insertInfo(salesVO);
        return "content/saleList";
    }

    //판매목록
    @GetMapping("/saleList")
    public String saleInfo(SalesVO salesVO, Model model){
        List<SalesVO> carList = carInfoService.carList(salesVO);
        model.addAttribute("carList", carList);
         return "content/saleList";
    }
}
