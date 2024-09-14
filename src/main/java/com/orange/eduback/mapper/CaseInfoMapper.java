package com.orange.eduback.mapper;

import com.orange.eduback.domain.CaseInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author admin
* @description 针对表【case_info(案例)】的数据库操作Mapper
* @createDate 2024-09-05 08:36:18
* @Entity com.orange.eduback.domain.CaseInfo
*/
public interface CaseInfoMapper extends BaseMapper<CaseInfo> {

    List<CaseInfo> findCasesByType(String type);
}




