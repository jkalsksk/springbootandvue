package com.sakura.web.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sakura.web.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Administrator
* @description 针对表【t_user(spring+vue 的用户表)】的数据库操作Service
* @createDate 2022-10-27 20:00:10
*/
public interface UserService extends IService<User> {

    Page<User> listPage(Integer currentPage, Integer pageSize, String search);

    boolean checkNickName(String name);

    boolean updateUserById(User user);
}
