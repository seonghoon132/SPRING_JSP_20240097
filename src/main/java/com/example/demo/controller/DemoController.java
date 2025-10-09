package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.domain.TestDB;
import com.example.demo.model.service.TestService; // 최상단 서비스 클래스 연동 추rk



@Controller
public class DemoController {
    @GetMapping("/hello") // 전송 방식 GET
    public String hello(Model model) {
    model.addAttribute("data", " 방갑습니다."); // model 설정
    return "hello"; // hello.html 연결
    } 

    @GetMapping("/about_detailed")
    public String about() {
    return "about_detailed";
 }
     @GetMapping("/test1")
     public String thymeleaf_test1(Model model) {
        model.addAttribute("data1", "<h2> 방갑습니다 </h2>");
        model.addAttribute("data2", "태그의 속성 값");
        model.addAttribute("link", 01);
        model.addAttribute("name", "홍길동");
        model.addAttribute("para1", "001");
        model.addAttribute("para2", 002);  //컨트롤러를 통해 데이터를 전달함
    return "thymeleaf_test1";
     }
   

     @Autowired
 TestService testService; // DemoController 클래스 아래 객체 생성
// 하단에 맵핑 이어서 추가

    @GetMapping("/testdb")
    public String getAllTestDBs(Model model) {
        TestDB test = testService.findByName("홍길동");
        model.addAttribute("data4", test);
        System.out.println("데이터 출력 디버그 : " + test);
        
        TestDB test01 = testService.findByName("아저씨");
        model.addAttribute("data5", test01);
        System.out.println("데이터 출력 디버그 : " + test01);

        TestDB test02 = testService.findByName("꾸러기");
        model.addAttribute("data6", test02);
        System.out.println("데이터 출력 디버그 : " + test02);

    return "testdb";
    }  
     


}
