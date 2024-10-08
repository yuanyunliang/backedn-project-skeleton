package com.orange.eduback.mapper;

import com.orange.eduback.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
* @author admin
* @description 针对表【user】的数据库操作Mapper
* @createDate 2024-07-29 22:48:16
* @Entity com.orange.eduback.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

    User findOneByUsername(@Param("userName") String userName);

    @Select("SELECT * FROM user WHERE user_email = #{userEmail}")
    User findOneByMail(String userEmail);
}




