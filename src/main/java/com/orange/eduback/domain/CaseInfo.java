package com.orange.eduback.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * @TableName case_info
 */
@TableName(value ="case_info")
@Data
public class CaseInfo implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String caseName;

    private String caseDesc;

    private String caseType;

    private String caseContent;

    private BigDecimal casePrice;

    private String author;

    private Integer watchCount;

    private Integer likeCount;

    private Integer favCount;

    private Date createdAt;

    private Date updateAt;

    private byte[] caseCover;

    private static final long serialVersionUID = 1L;
}