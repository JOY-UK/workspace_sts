package com.green.Second.controller;

import com.green.Second.ResumeVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.Name;
import javax.print.attribute.standard.MediaSize;

@Controller
public class resumeController {

    @GetMapping("/r1")
    public String first(){
        return "resume";
    }

    @PostMapping("/r2")
    public String resume_Info(@RequestParam(name = "name") String name,
                              @RequestParam(name = "tell") String tell,
                              Model model){
        model.addAttribute("name", name);
        model.addAttribute("tell", tell);
        return "resume_Info" ;
    }

    @PostMapping ("/r3")
    public String resume_check(ResumeVO resumeVO) {
        System.out.println(resumeVO);
        return "resume_check" ;
    }





}