package com.sakura.web.mapper;

import com.sakura.web.pojo.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sakura.web.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
* @author Administrator
* @description 针对表【t_account】的数据库操作Mapper
* @createDate 2022-10-29 10:11:17
* @Entity com.sakura.web.pojo.Account
*/
@Mapper
public interface AccountMapper extends BaseMapper<Account> {

    /**
     * 管理员登入功能
     * @param username 登入账户
     * @param password 登入密码
     * @return 登入成功的 账户
     */
    @Select("select * from t_account where user_name = #{username} and pass_word = #{password};")
    Account selectByUserNameAndPasswordUser(@Param("username") String username, @Param("password") String password);
}




