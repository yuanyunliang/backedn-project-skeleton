package com.orange.eduback.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orange.eduback.domain.Course;
import com.orange.eduback.service.CourseService;
import com.orange.eduback.mapper.CourseMapper;
import org.springframework.stereotype.Service;

/**
* @author admin
* @description 针对表【course】的数据库操作Service实现
* @createDate 2024-07-25 19:09:54
*/
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course>
    implements CourseService{

}




