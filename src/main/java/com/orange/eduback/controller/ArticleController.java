package com.orange.eduback.controller;

import com.orange.eduback.common.PlainResult;
import com.orange.eduback.domain.Article;
import com.orange.eduback.dto.ArticleDto;
import com.orange.eduback.service.ArticleService;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private static final Logger log = LoggerFactory.getLogger(ArticleController.class);
    @Resource
    private ArticleService articleService;
    //上传文章课程
    @PostMapping("/upload")
    public PlainResult<String> uploadArticleCourse(@RequestBody ArticleDto articleDto) {
        articleService.uploadArticle(articleDto);
        return PlainResult.success("success");
    }

    //查询某类所有文章
    @GetMapping("/all/{type}")
    public PlainResult<List<Article>> getAllArticles(@PathVariable("type") String type) {
        log.info("get all articles***********,the type is:{}",type);
        return PlainResult.success(articleService.getAllArticles(type));
    }

    //根据id查询文章
    @GetMapping("/getArticleById/{id}")
    public PlainResult<Article> getArticleById(@PathVariable("id") String id) {
        log.info("get article by id:{}",id);
        return PlainResult.success(articleService.getArticleById(id));
    }

    //根据id更新文章的收藏数
    @PostMapping("/updateFav")
    public PlainResult<Article> updateFav(@RequestBody Map<String,Integer> reqMap) {
        Integer id = reqMap.get("id");
        Integer num = reqMap.get("num");
        log.info("update fav by id:{},add fav: {}",id,num);
        return PlainResult.success(articleService.updateFav(id,num));
    }

    //根据id更新文章的点赞数
    @PostMapping("/updateLike")
    public PlainResult<Article> updateLike(@RequestBody Map<String,Integer> reqMap) {
        Integer id = reqMap.get("id");
        Integer num = reqMap.get("num");
        log.info("update like by id:{},add like: {}",id,num);
        return PlainResult.success(articleService.updateLike(id,num));
    }

    //根据id更新文章的观看数
    @PostMapping("/updateWatch")
    public PlainResult<Article> updateWatch(@RequestBody Map<String,Integer> reqMap) {
        Integer id = reqMap.get("id");
        Integer num = reqMap.get("num");
        log.info("update watch by id:{},add watch: {}",id,num);
        return PlainResult.success(articleService.updateWatch(id,num));
    }

}
