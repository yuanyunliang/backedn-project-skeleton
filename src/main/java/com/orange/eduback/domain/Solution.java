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
 * @TableName solution
 */
@TableName(value ="solution")
@Data
public class Solution implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String solutionName;

    private String solutionDesc;

    private String solutionType;

    private String solutionContent;

    private BigDecimal solutionPrice;

    private String author;

    private Integer watchCount;

    private Integer likeCount;

    private Integer favCount;

    private Date createdAt;

    private Date updateAt;

    private byte[] solutionCover;

    private static final long serialVersionUID = 1L;
}