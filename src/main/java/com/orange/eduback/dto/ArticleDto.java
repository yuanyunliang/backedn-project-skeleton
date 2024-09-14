package com.orange.eduback.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ArticleDto {
    private Long id;
    private String courseName;
    private String courseType;
    private String courseClass;
    private String courseDesc;
    private BigDecimal coursePrice;
    private String courseCover;
    private String courseContent;
    private String author;
    private Integer watchCount;
    private Integer likeCount;
    private Integer favCount;

}
