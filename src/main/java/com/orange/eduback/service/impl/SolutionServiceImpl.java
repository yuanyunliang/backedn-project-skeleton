package com.orange.eduback.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orange.eduback.domain.Solution;
import com.orange.eduback.dto.SolutionDto;
import com.orange.eduback.service.SolutionService;
import com.orange.eduback.mapper.SolutionMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author admin
* @description 针对表【solution(方案表)】的数据库操作Service实现
* @createDate 2024-09-04 10:26:26
*/
@Service
public class SolutionServiceImpl extends ServiceImpl<SolutionMapper, Solution>
    implements SolutionService{

    private final SolutionMapper solutionMapper;

    public SolutionServiceImpl(SolutionMapper solutionMapper) {
        this.solutionMapper = solutionMapper;
    }

    @Override
    public void uploadSolution(SolutionDto solutionDto) {
        Solution solution = new Solution();
        solution.setSolutionName(solutionDto.getSolutionName());
        solution.setSolutionType(solutionDto.getSolutionType());
        solution.setSolutionDesc(solutionDto.getSolutionDesc());
        solution.setSolutionPrice(solutionDto.getSolutionPrice());
        solution.setSolutionCover(solutionDto.getSolutionCover()==null?solutionDto.getSolutionCover().getBytes():null);
        solution.setSolutionContent(solutionDto.getSolutionContent());
        solution.setAuthor(solutionDto.getAuthor());
        solution.setWatchCount(solutionDto.getWatchCount());
        solution.setLikeCount(solutionDto.getLikeCount());
        solution.setFavCount(solutionDto.getFavCount());
        this.save(solution);
    }

    @Override
    public List<Solution> getAllSolutions(String type) {
        if("all".equals(type)){
            return solutionMapper.selectList(null);
        }
       // return this.lambdaQuery().eq(Solution::getSolutionType,type).list();
        return solutionMapper.findSolutionsByType(type);
    }

    @Override
    public Solution getSolutionById(String id) {
        return this.getById(id);
    }

    @Override
    public Solution updateFav(Integer id, Integer num) {
        Solution solution = this.getById(id);
        solution.setFavCount(solution.getFavCount()+ num);
        this.updateById(solution);
        return solution;
    }

    @Override
    public Solution updateLike(Integer id, Integer num) {
        Solution solution = this.getById(id);
        solution.setLikeCount(solution.getLikeCount()+ num);
        this.updateById(solution);
        return solution;
    }

    @Override
    public Solution updateWatch(Integer id, Integer num) {
        Solution solution = this.getById(id);
        solution.setWatchCount(solution.getWatchCount()+ num);
        this.updateById(solution);
        return solution;
    }
}




