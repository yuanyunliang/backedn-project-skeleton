package com.orange.eduback.service;

import com.orange.eduback.domain.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.orange.eduback.dto.ArticleDto;

import java.util.List;

/**
* @author admin
* @description 针对表【article】的数据库操作Service
* @createDate 2024-08-28 16:09:11
*/
public interface ArticleService extends IService<Article> {

    void uploadArticle(ArticleDto articleDto);

    List<Article> getAllArticles(String type);

    Article getArticleById(String id);

    Article updateFav(Integer id, Integer fav);

    Article updateLike(Integer id, Integer like);

    Article updateWatch(Integer id, Integer watch);
}
