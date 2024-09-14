package com.orange.eduback.controller;

import com.orange.eduback.common.PlainResult;
import com.orange.eduback.dto.ArticleDto;
import com.orange.eduback.dto.CourseDto;
import com.orange.eduback.dto.CourseResponseDto;
import com.orange.eduback.domain.Course;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import com.orange.eduback.service.CourseService;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/course")
public class CourseController {

    private static final Logger log = LoggerFactory.getLogger(CourseController.class);
    @Resource
    private CourseService courseService;
    //上传视频课程
    @PostMapping("/upload")
    public PlainResult<String> uploadCourse(@RequestBody CourseDto courseDto) {
        courseService.uploadCourse(courseDto);
        return PlainResult.success("success");
    }

    //上传视频课程封面
    @PostMapping("/uploadCover")
    public PlainResult<CourseResponseDto> uploadCourseCover(MultipartFile file) {
        log.info("********uploadCourseCover信息:{}************", file);
        return PlainResult.success(courseService.uploadCourseCover(file));
    }

    //上传视频课程视频
    @PostMapping("/uploadVideo")
    public PlainResult<CourseResponseDto> uploadCourseVideo(MultipartFile file) {
        log.info("********uploadCourseVideo信息:{}************", file);
        return PlainResult.success(courseService.uploadCourseVideo(file));
    }

    @GetMapping("/getCourseById/{id}")
    public Course getCourseById(@PathVariable("id") String id) {
        log.info("get course by id:{}",id);
        return courseService.getCourseById(id);
    }

    //查找所有课程信息
    @GetMapping("/all/{type}")
    public List<Course> getAllCourses(@PathVariable("type") String type) {
        log.info("get all courses***********");
        return courseService.getAllCourses(type);
    }

}
