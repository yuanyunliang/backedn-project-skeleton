package com.orange.eduback.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orange.eduback.domain.CaseInfo;
import com.orange.eduback.dto.CaseInfoDto;
import com.orange.eduback.service.CaseInfoService;
import com.orange.eduback.mapper.CaseInfoMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author admin
* @description 针对表【case_info(案例)】的数据库操作Service实现
* @createDate 2024-09-05 08:36:18
*/
@Service
public class CaseInfoServiceImpl extends ServiceImpl<CaseInfoMapper, CaseInfo>
    implements CaseInfoService{

    @Resource
    private CaseInfoMapper caseInfoMapper;
    @Override
    public void uploadCase(CaseInfoDto caseInfoDto) {
        CaseInfo caseInfo = new CaseInfo();
        caseInfo.setCaseName(caseInfoDto.getCaseName());
        caseInfo.setCaseDesc(caseInfoDto.getCaseDesc());
        caseInfo.setCaseType(caseInfoDto.getCaseType());
        caseInfo.setCaseContent(caseInfoDto.getCaseContent());
        caseInfo.setCaseCover(caseInfoDto.getCaseCover()==null?null:caseInfoDto.getCaseCover().getBytes());
        caseInfo.setCasePrice(caseInfoDto.getCasePrice());
        caseInfo.setAuthor(caseInfoDto.getAuthor());
        caseInfo.setWatchCount(caseInfoDto.getWatchCount());
        caseInfo.setLikeCount(caseInfoDto.getLikeCount());
        caseInfo.setFavCount(caseInfoDto.getFavCount());

        caseInfoMapper.insert(caseInfo);
    }

    @Override
    public List<CaseInfo> getAllCases(String type) {
        if("all".equals(type)){
            return caseInfoMapper.selectList(null);
        }
        return caseInfoMapper.findCasesByType(type);
    }

    @Override
    public CaseInfo getCaseById(String id) {
        return caseInfoMapper.selectById(id);
    }

    @Override
    public CaseInfo updateFav(Integer id, Integer num) {
         CaseInfo caseInfo = caseInfoMapper.selectById(id);
         caseInfo.setFavCount(caseInfo.getFavCount()+ num);
         this.updateById(caseInfo);
         return caseInfo;
    }

    @Override
    public CaseInfo updateLike(Integer id, Integer num) {
        CaseInfo caseInfo = caseInfoMapper.selectById(id);
        caseInfo.setLikeCount(caseInfo.getLikeCount()+ num);
        this.updateById(caseInfo);
        return caseInfo;
    }

    @Override
    public CaseInfo updateWatch(Integer id, Integer num) {
        CaseInfo caseInfo = caseInfoMapper.selectById(id);
        caseInfo.setWatchCount(caseInfo.getWatchCount()+ num);
        this.updateById(caseInfo);
        return caseInfo;
    }
}




