package com.sakura.web.mapper;

import com.sakura.web.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
* @author sakura
* @description 针对表【t_user(spring+vue 的用户表)】的数据库操作Mapper
* @createDate 2022-10-27 20:00:10
* @Entity com.sakura.web.pojo.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {
}




