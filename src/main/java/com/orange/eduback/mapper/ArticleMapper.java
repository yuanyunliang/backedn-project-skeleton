package com.orange.eduback.mapper;

import com.orange.eduback.domain.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author admin
* @description 针对表【article】的数据库操作Mapper
* @createDate 2024-08-28 16:09:11
* @Entity com.orange.eduback.domain.Article
*/
public interface ArticleMapper extends BaseMapper<Article> {

    List<Article> findArticlesByType(String type);

    int updateArticle(Article article);

}




