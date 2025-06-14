package com.company.firstproject.dto;

import com.company.firstproject.entity.Article;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class ArticleForm {

    private Long id;
    private String title;
    private String content;

    public Article toNoIdEnity() {
        return new Article(null, title, content);
    }

    public Article toEnity() {
        return new Article(id, title, content);
    }

}

