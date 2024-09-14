package com.orange.eduback.controller;

import com.orange.eduback.common.PlainResult;
import com.orange.eduback.domain.Article;
import com.orange.eduback.domain.Solution;
import com.orange.eduback.dto.SolutionDto;
import com.orange.eduback.service.SolutionService;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/solution")
public class SolutionController {

    private static final Logger log = LoggerFactory.getLogger(SolutionController.class);
    @Resource
    private SolutionService solutionService;
    //上传方案
    @PostMapping("/upload")
    public PlainResult<String> uploadSolution(@RequestBody SolutionDto solutionDto) {
        solutionService.uploadSolution(solutionDto);
        return PlainResult.success("success");
    }
    //查询某类所有方案
    @GetMapping("/all/{type}")
    public PlainResult<List<Solution>> getAllSolutions(@PathVariable("type") String type) {
        log.info("get all solutions by type:{}",type);
        return PlainResult.success(solutionService.getAllSolutions(type));
    }

    //根据id查询方案
    @GetMapping("/getSolutionById/{id}")
    public PlainResult<Solution> getSolutionById(@PathVariable("id") String id) {
        return PlainResult.success(solutionService.getSolutionById(id));
    }

    //根据id更新收藏数
    @PostMapping("/updateFav")
    public PlainResult<Solution> updateFav(@RequestBody Map<String,Integer> reqMap) {
        Integer id = reqMap.get("id");
        Integer num = reqMap.get("num");
        log.info("update fav by id:{},add fav: {}",id,num);
        return PlainResult.success(solutionService.updateFav(id,num));
    }

    //根据id更新点赞数
    @PostMapping("/updateLike")
    public PlainResult<Solution> updateLike(@RequestBody Map<String,Integer> reqMap) {
        Integer id = reqMap.get("id");
        Integer num = reqMap.get("num");
        log.info("update like by id:{},add like: {}",id,num);
        return PlainResult.success(solutionService.updateLike(id,num));
    }

    //根据id更新观看数
    @PostMapping("/updateWatch")
    public PlainResult<Solution> updateWatch(@RequestBody Map<String,Integer> reqMap) {
        Integer id = reqMap.get("id");
        Integer num = reqMap.get("num");
        log.info("update watch by id:{},add watch: {}",id,num);
        return PlainResult.success(solutionService.updateWatch(id,num));
    }
}
