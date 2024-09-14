package com.orange.eduback.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SolutionDto {
    private Long id;
    private String solutionName;
    private String solutionType;
    private String solutionDesc;
    private BigDecimal solutionPrice;
    private String solutionCover;
    private String solutionContent;
    private String author;
    private Integer watchCount;
    private Integer likeCount;
    private Integer favCount;
}
