package com.orange.eduback.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CaseInfoDto {
    private Long id;
    private String caseName;
    private String caseType;
    private String caseDesc;
    private BigDecimal casePrice;
    private String caseCover;
    private String caseContent;
    private String author;
    private Integer watchCount;
    private Integer likeCount;
    private Integer favCount;
}
