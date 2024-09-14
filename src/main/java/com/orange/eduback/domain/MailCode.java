package com.orange.eduback.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName mail_code
 */
@TableName(value ="mail_code")
@Data
public class MailCode implements Serializable {
    private Integer id;

    private String email;

    private String verifyCode;

    private static final long serialVersionUID = 1L;
}