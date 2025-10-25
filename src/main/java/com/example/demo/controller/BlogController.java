 package com.example.demo.controller;
 import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.example.demo.model.domain.Article;
import com.example.demo.model.service.AddArticleRequest;
import com.example.demo.model.service.BlogService; // 최상단 서비스 클래스 연동 추rk

import lombok.RequiredArgsConstructor;

import java.util.List;



@Controller
public class BlogController {
    
   
    @Autowired
    BlogService blogService; // DemoController 클래스 아래 객체 생성

    @GetMapping("/article_list") // 게시판 링크 지정
    public String article_list(Model model) {
        List<Article> list = blogService.findAll(); // 게시판 리스트 전구모양 -> import
        model.addAttribute("articles", list); // 모델에 추가
        return "article_list"; // .HTML 연결
    }
    
    
    @GetMapping("/article_edit/{id}") // 게시판링크지정
    public String article_edit(Model model, @PathVariable Long id) {
        Optional<Article> list = blogService.findById(id); // 선택한게시판글
            
        if (list.isPresent()) {
                model.addAttribute("article", list.get()); // 존재하면Article 객체를모델에추가
                } else {
            // 처리할로직추가(예: 오류페이지로리다이렉트, 예외처리등)
                return "error_page/article_error"; // 오류처리페이지로연결
            }
                return "article_edit"; // .HTML 연결
    }
    @PutMapping("/api/article_edit/{id}")
    public String updateArticle(@PathVariable Long id, @ModelAttribute AddArticleRequest request) {
        blogService.update(id, request);
        return "redirect:/article_list"; // 글 수정 이후 .html 연결
}
    @DeleteMapping("/api/article_delete/{id}")
        public String deleteArticle(@PathVariable Long id) {
        blogService.delete(id);
        return "redirect:/article_list";
 }


    @PostMapping("/articles")
        public String addArticle(@ModelAttribute AddArticleRequest request) {
            blogService.save(request);  // DB 저장
    return "redirect:/article_list";  // 저장 후 목록으로 이동
}
    @ControllerAdvice
        public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)   //어떤 에러 종류를 잡을지 지정
        public String handleTypeMismatch(MethodArgumentTypeMismatchException ex, Model model) {
        
            model.addAttribute("message", "잘못된 게시글 접근입니다.");
            return "error_page/article_error"; // <- 에러 페이지로 연결
    }
}
        
}

