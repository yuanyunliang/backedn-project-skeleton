package com.orange.eduback.service;

import com.orange.eduback.domain.Solution;
import com.baomidou.mybatisplus.extension.service.IService;
import com.orange.eduback.dto.SolutionDto;

import java.util.List;

/**
* @author admin
* @description 针对表【solution(方案表)】的数据库操作Service
* @createDate 2024-09-04 10:26:26
*/
public interface SolutionService extends IService<Solution> {

    void uploadSolution(SolutionDto solutionDto);

    List<Solution> getAllSolutions(String type);

    Solution getSolutionById(String id);

    Solution updateFav(Integer id, Integer num);

    Solution updateLike(Integer id, Integer num);

    Solution updateWatch(Integer id, Integer num);
}
