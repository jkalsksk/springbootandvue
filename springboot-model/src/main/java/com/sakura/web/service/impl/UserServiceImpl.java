package com.sakura.web.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sakura.web.pojo.User;
import com.sakura.web.service.UserService;
import com.sakura.web.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Administrator
* @description 针对表【t_user(spring+vue 的用户表)】的数据库操作Service实现
* @createDate 2022-10-27 20:00:10
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    private UserMapper userMapper;


    @Override
    public Page<User> listPage(Integer currentPage, Integer pageSize, String search) {

        // 分页查询的对象
        Page<User> page = new Page<>(currentPage, pageSize);
        // 分页查询的 条
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(User::getNickName, search);
        }

        // 分页查询
        return userMapper.selectPage(page, wrapper);
    }

    @Override
    public boolean checkNickName(String name) {

        // 查询数据库 发现重复名字 则 返回 true 不重复 则为 false
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("nick_name", name);

        User user = userMapper.selectOne(userQueryWrapper);

        if (user != null) {
            return true;
        }

        return false;
    }

    @Override
    public boolean updateUserById(User user) {

        int i = userMapper.updateById(user);

        if (i > 0) {
            return true;
        }

        return false;
    }
}




