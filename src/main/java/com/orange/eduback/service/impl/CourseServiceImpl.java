package com.orange.eduback.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orange.eduback.domain.Course;
import com.orange.eduback.dto.CourseDto;
import com.orange.eduback.dto.CourseResponseDto;
import com.orange.eduback.service.CourseService;
import com.orange.eduback.mapper.CourseMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

/**
* @author admin
* @description 针对表【course】的数据库操作Service实现
* @createDate 2024-07-25 19:09:54
*/
@Slf4j
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course>
    implements CourseService{

    private final CourseMapper courseMapper;

    public CourseServiceImpl(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    @Override
    public void uploadCourse(CourseDto courseDto) {
        log.info("^^^^^^^^^^uploadCourse:{}", courseDto);
        Course course = new Course();
        course.setCourseName(courseDto.getCourseName());
        //course.setCourseCover(courseDto.getCourseCover());
        course.setCourseUrl(courseDto.getCourseUrl());
        course.setCourseDescription(courseDto.getCourseDesc());
        course.setCourseFee(new BigDecimal(courseDto.getCoursePrice()));
        course.setCourseType(courseDto.getCourseType());
        course.setCourseDuration(courseDto.getCourseDuration());
        course.setCourseStatus(courseDto.getCourseStatus());
        courseMapper.insert(course);
    }

    @Override
    public CourseResponseDto uploadCourseCover(MultipartFile file) {
        CourseResponseDto courseResponseDto = new CourseResponseDto();
        //获取文件原始的名称
        String originalFilename = file.getOriginalFilename();
        //String picPath = "/Users/admin/vueProjects/eduFront/src/assets/digitalCourses/";
        String picPath = "/Users/admin/Documents/orangeTech/project_space/eduFront/src/assets/covers/";
        //file.transferTo的异常直接抛出去
        //再次存储的时候使用原来的文件名
        try {
            file.transferTo(new File(picPath+originalFilename));
        }catch (Exception e){
            log.error("uploadCourseCover error:{}", e);
        }
        courseResponseDto.setCourseCover(picPath+originalFilename);
        return courseResponseDto;
    }

    @Override
    public CourseResponseDto uploadCourseVideo(MultipartFile file) {
        CourseResponseDto courseResponseDto = new CourseResponseDto();
        //获取文件原始的名称
        String originalFilename = file.getOriginalFilename();
        //String videoPath = "/Users/admin/vueProjects/eduFront/src/assets/videos/";
        String videoPath = "/Users/admin/Documents/orangeTech/project_space/eduFront/src/assets/videos/";
        //file.transferTo的异常直接抛出去
        //再次存储的时候使用原来的文件名
        try {
            file.transferTo(new File(videoPath+originalFilename));
        }catch (Exception e){
            log.error("uploadCourseVideo error:{}", e);
        }
        courseResponseDto.setCourseUrl(videoPath+originalFilename);
        return courseResponseDto;
    }

    @Override
    public Course getCourseById(String id) {
        return courseMapper.selectById(id);
    }

    @Override
    public List<Course> getAllCourses(String type) {
        return courseMapper.findCoursesByType(type);
    }
}




