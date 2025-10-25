 package com.example.demo.model.service;

 import java.util.List;
 import java.util.Optional;

 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import com.example.demo.model.domain.Article;
 import com.example.demo.model.repository.BlogRepository;
 import lombok.RequiredArgsConstructor;
 
 @Service
 @RequiredArgsConstructor // 생성자자동생성(부분)
 
 public class BlogService {
    @Autowired // 객체주입자동화, 생성자1개면생략가능
    private final BlogRepository blogRepository; // 리포지토리선언
    
    

     public Article save(AddArticleRequest request){
        
        return blogRepository.save(request.toEntity());
 }
 
 public Optional<Article> findById(Long id) { // 게시판특정글조회
    return blogRepository.findById(id);
 }
    
 public void update(Long id, AddArticleRequest request) {
    Optional<Article> optionalArticle = blogRepository.findById(id); // 단일글조회
    optionalArticle.ifPresent(article -> { //값이있으면
      article.update(request.getTitle(), request.getContent()); // 값을수정
      blogRepository.save(article); // Article 객체에저장
    });
 }
 
 public List<Article> findAll() { // 게시판전체목록조회
        return blogRepository.findAll();
    }
 public void delete(Long id) {
    blogRepository.deleteById(id);
 }
}
