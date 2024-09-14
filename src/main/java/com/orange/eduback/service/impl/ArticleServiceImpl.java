package com.orange.eduback.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orange.eduback.domain.Article;
import com.orange.eduback.dto.ArticleDto;
import com.orange.eduback.service.ArticleService;
import com.orange.eduback.mapper.ArticleMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author admin
* @description 针对表【article】的数据库操作Service实现
* @createDate 2024-08-28 16:09:11
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{

    @Resource
    private ArticleMapper articleMapper;
    @Override
    public void uploadArticle(ArticleDto articleDto) {
        Article article = new Article();
        article.setCourseName(articleDto.getCourseName());
        article.setCourseCover(articleDto.getCourseCover()==null?articleDto.getCourseCover().getBytes():null);
        article.setCourseType(articleDto.getCourseType());
        article.setCourseDesc(articleDto.getCourseDesc());
        article.setCoursePrice(articleDto.getCoursePrice());
        article.setCourseClass(articleDto.getCourseClass());
        article.setCourseContent(articleDto.getCourseContent());
        article.setAuthor(articleDto.getAuthor());

        articleMapper.insert(article);
    }

    @Override
    public List<Article> getAllArticles(String type) {
        if("all".equals(type)){
            return articleMapper.selectList(null);
        }
        return articleMapper.findArticlesByType(type);
    }

    @Override
    public Article getArticleById(String id) {
        return articleMapper.selectById(id);
    }

    @Override
    public Article updateFav(Integer id, Integer fav) {
        Article article = articleMapper.selectById(id);
        article.setFavCount(article.getFavCount()+ fav);
        articleMapper.updateArticle(article);
        return article;
    }

    @Override
    public Article updateLike(Integer id, Integer like) {
        Article article = articleMapper.selectById(id);
        article.setLikeCount(article.getLikeCount()+ like);
        articleMapper.updateArticle(article);
        return article;
    }

    @Override
    public Article updateWatch(Integer id, Integer watch) {
        Article article = articleMapper.selectById(id);
        article.setWatchCount(article.getWatchCount()+ watch);
        articleMapper.updateArticle(article);
        return article;
    }
}




