package com.company.firstproject.service;

import com.company.firstproject.dto.ArticleForm;
import com.company.firstproject.entity.Article;
import com.company.firstproject.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService {

    private final ArticleRepository articleRepository;


    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toNoIdEnity();
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {

        // 1. DTO -> 엔티티 변환하기
        Article article = dto.toEnity();
        log.info("id: {}, article: {}", id, article.toString());

        // 2. 타킷 조회하기
        Article target = articleRepository.findById(id).orElse(null);

        // 3. 잘못된 요청 처리하기
        if(target == null || id != article.getId()){
            log.info("id: {}, article: {}", id, article.toString());
            return null;
        }

        // 4. 업데이트하기
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;
    }

    public Article delete(Long id) {

        // 1. 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);

        // 2. 잘못된 요청 처리하기
        if(target == null) {
            return null;
        }
        // 3. 대상 삭제하기
        articleRepository.delete(target);
        return target;
    }

    public List<Article> createArticles(List<ArticleForm> dtos) {

        // 1. dto 묶음을 엔티리 묶음으로 변환하기
        List<Article> articleList = dtos.stream()
                .map(dto->dto.toNoIdEnity())
                .collect(Collectors.toList());

        // 2. 엔티티 묶음을 DB에 저장하기
        articleList.stream()
                .forEach(article -> articleRepository.save(article));

        // 3. 강제 예외 발생시키기
        try {
            articleRepository.findById(-1L)
                    .orElseThrow(() -> new IllegalAccessException("결재실패"));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        // 4. 결과 값 변환하기
        return articleList;
    }
}
