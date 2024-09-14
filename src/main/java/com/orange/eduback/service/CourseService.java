package com.orange.eduback.service;

import com.orange.eduback.domain.Course;
import com.orange.eduback.dto.CourseDto;
import com.baomidou.mybatisplus.extension.service.IService;
import com.orange.eduback.dto.CourseResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
* @author admin
* @description 针对表【course】的数据库操作Service
* @createDate 2024-07-25 19:09:54
*/
public interface CourseService extends IService<Course> {

    void uploadCourse(CourseDto courseDto);

    CourseResponseDto uploadCourseCover(MultipartFile file);

    CourseResponseDto uploadCourseVideo(MultipartFile file);

    Course getCourseById(String id);

    List<Course> getAllCourses(String type);
}
