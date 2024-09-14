package com.orange.eduback.mapper;

import com.orange.eduback.domain.Solution;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author admin
* @description 针对表【solution(方案表)】的数据库操作Mapper
* @createDate 2024-09-04 10:26:26
* @Entity com.orange.eduback.domain.Solution
*/
public interface SolutionMapper extends BaseMapper<Solution> {

    List<Solution> findSolutionsByType(String type);
}




