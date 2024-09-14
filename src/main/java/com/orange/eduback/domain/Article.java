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
 * @TableName article
 */
@TableName(value ="article")
@Data
public class Article implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String courseName;

    private String courseDesc;

    private String courseType;

    private String courseClass;

    private BigDecimal coursePrice;

    private String courseContent;

    private String author;

    private Integer watchCount;

    private Integer likeCount;

    private Integer favCount;

    private Date createdAt;

    private Date updatedAt;

    private byte[] courseCover;

    private static final long serialVersionUID = 1L;
}