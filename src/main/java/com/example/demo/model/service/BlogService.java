 package com.example.demo.model.service;

 import java.util.List;
 import java.util.Optional;

 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
 import com.example.demo.model.domain.Article;
 import com.example.demo.model.repository.BlogRepository;
 import lombok.RequiredArgsConstructor;
 import com.example.demo.model.repository.BoardRepository;
 import com.example.demo.model.domain.Board;

 @Service
 @RequiredArgsConstructor // 생성자자동생성(부분)
 
 public class BlogService {
    @Autowired // 객체주입자동화, 생성자1개면생략가능
    private final BlogRepository blogRepository; // 리포지토리선언
    private final BoardRepository blogRepository2; // 리포지토리선언
    
    

      // public Article save(AddArticleRequest request){
        
      //   return blogRepository.save(request.toEntity());

      public Board save(AddArticleRequest request){
         // DTO가 없는 경우 이곳에 직접 구현 가능
       return blogRepository2.save(request.toEntity());
 }
 
 
//  public List<Board> findAll() { // 게시판 전체 목록 조회
//    return blogRepository2.findAll();
//  }

 public Optional<Board> findById(Long id) { // 게시판 특정 글 조회
    return blogRepository2.findById(id);
}
 
//  public Optional<Article> findById(Long id) { // 게시판특정글조회
//     return blogRepository.findById(id);
//  }
    
 public void update(Long id, AddArticleRequest request) {
    Optional<Article> optionalArticle = blogRepository.findById(id); // 단일글조회
    optionalArticle.ifPresent(article -> { //값이있으면
      article.update(request.getTitle(), request.getContent()); // 값을수정
      blogRepository.save(article); // Article 객체에저장
    });
 }
  
public Page<Board> findAll(Pageable pageable) {
   return blogRepository2.findAll(pageable);
 }
public Page<Board> searchByKeyword(String keyword, Pageable pageable) {
   return blogRepository2.findByTitleContainingIgnoreCase(keyword, pageable);
 } // LIKE 검색 제공(대소문자 무시)

public void delete(Long id) {
    blogRepository.deleteById(id);
 }
}
