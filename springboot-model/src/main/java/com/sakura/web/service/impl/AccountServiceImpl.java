package com.sakura.web.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sakura.web.common.Result;
import com.sakura.web.pojo.Account;
import com.sakura.web.service.AccountService;
import com.sakura.web.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【t_account】的数据库操作Service实现
* @createDate 2022-10-29 10:11:17
*/
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account>
    implements AccountService{

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Result checkLoginAccount(String username, String password) {

        // 判断 用户名 和 密码是否 为 null 或者 空字符串
        if (StrUtil.isAllBlank(username, password) || StrUtil.isAllEmpty(username, password))
            return Result.error("-1", "亲 您的登入信息未填写完毕哟!");

        Account account = accountMapper.selectByUserNameAndPasswordUser(username, password);

        if (account == null)
            return Result.error("-1", "用户名或者密码错误 亲 请您检查一下哟!");

        return Result.success(account.getUserName());
    }
}




