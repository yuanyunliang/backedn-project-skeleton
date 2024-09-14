package com.orange.eduback.service;

import com.orange.eduback.domain.CaseInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.orange.eduback.dto.CaseInfoDto;

import java.util.List;

/**
* @author admin
* @description 针对表【case_info(案例)】的数据库操作Service
* @createDate 2024-09-05 08:36:18
*/
public interface CaseInfoService extends IService<CaseInfo> {

    void uploadCase(CaseInfoDto caseInfoDto);

    List<CaseInfo> getAllCases(String type);

    CaseInfo getCaseById(String id);

    CaseInfo updateFav(Integer id, Integer num);

    CaseInfo updateLike(Integer id, Integer num);

    CaseInfo updateWatch(Integer id, Integer num);
}
