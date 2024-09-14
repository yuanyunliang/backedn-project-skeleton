package com.orange.eduback.controller;

import com.orange.eduback.common.PlainResult;
import com.orange.eduback.domain.CaseInfo;
import com.orange.eduback.dto.CaseInfoDto;
import com.orange.eduback.service.CaseInfoService;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/case")
public class CaseController {

    private static final Logger log = LoggerFactory.getLogger(CaseController.class);
    @Resource
    private CaseInfoService caseInfoService;
    //上传方案
    @PostMapping("/upload")
    public PlainResult<String> uploadCase(@RequestBody CaseInfoDto caseInfoDto) {
        caseInfoService.uploadCase(caseInfoDto);
        return PlainResult.success("success");
    }
    //查询某类所有方案
    @GetMapping("/all/{type}")
    public PlainResult<List<CaseInfo>> getAllCases(@PathVariable("type") String type) {
        log.info("get all cases by type:{}",type);
        return PlainResult.success(caseInfoService.getAllCases(type));
    }

    //根据id查询方案
    @GetMapping("/getCaseById/{id}")
    public PlainResult<CaseInfo> getCaseById(@PathVariable("id") String id) {
        return PlainResult.success(caseInfoService.getCaseById(id));
    }

    //根据id更新收藏数
    @PostMapping("/updateFav")
    public PlainResult<CaseInfo> updateFav(@RequestBody Map<String,Integer> reqMap) {
        Integer id = reqMap.get("id");
        Integer num = reqMap.get("num");
        log.info("update fav by id:{},add fav: {}",id,num);
        return PlainResult.success(caseInfoService.updateFav(id,num));
    }

    //根据id更新点赞数
    @PostMapping("/updateLike")
    public PlainResult<CaseInfo> updateLike(@RequestBody Map<String,Integer> reqMap) {
        Integer id = reqMap.get("id");
        Integer num = reqMap.get("num");
        log.info("update like by id:{},add like: {}",id,num);
        return PlainResult.success(caseInfoService.updateLike(id,num));
    }

    //根据id更新观看数
    @PostMapping("/updateWatch")
    public PlainResult<CaseInfo> updateWatch(@RequestBody Map<String,Integer> reqMap) {
        Integer id = reqMap.get("id");
        Integer num = reqMap.get("num");
        log.info("update watch by id:{},add watch: {}",id,num);
        return PlainResult.success(caseInfoService.updateWatch(id,num));
    }
}
