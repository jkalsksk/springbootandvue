package com.sakura.web.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sakura.web.common.Result;
import com.sakura.web.pojo.Account;
import com.sakura.web.service.AccountService;
import com.sakura.web.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author Administrator
* @description 针对表【t_account】的数据库操作Service实现
* @createDate 2022-10-29 10:11:17
*/
@Service
@Transactional
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account>
    implements AccountService{

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Result registeredAccount(String username, String password) {

        // 先 查询 用户 是否存在 根据 用户名 查询
        // Account account = accountMapper.selectOne(query().eq("user_name", username));
        QueryWrapper<Account> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", username);
        Account acc = accountMapper.selectOne(wrapper);

        if (acc != null) {
            // 不为 null 则 说明 存在对象
            return Result.error("-1", "亲 该用户名已被占用哦!");
        }

        // 如果查询出来的结果 为 null 则 说明 该用户名可以注册  密码 进行加 密
        Account accountLogin = new Account(null, username,  DigestUtil.md5Hex(password));

        int insert = accountMapper.insert(accountLogin);

        if (insert > 0) {
            return Result.success();
        }

        return Result.error("-1", "服务器拥挤 请稍后尝试!");
    }

    @Override
    public Result checkLoginAccount(String username, String password) {

        // 判断 用户名 和 密码是否 为 null 或者 空字符串
        if (StrUtil.isAllBlank(username, password) || StrUtil.isAllEmpty(username, password))
            return Result.error("-1", "亲 您的登入信息未填写完毕哟!");

        Account account = accountMapper.selectByUserNameAndPasswordUser(username, DigestUtil.md5Hex(password));

        if (account == null)
            return Result.error("-1", "用户名或者密码错误 亲 请您检查一下哟!");

        return Result.success(account.getUserName());
    }
}




