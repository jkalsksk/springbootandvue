package com.sakura.web.service;

import com.sakura.web.common.Result;
import com.sakura.web.pojo.Account;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Administrator
* @description 针对表【t_account】的数据库操作Service
* @createDate 2022-10-29 10:11:17
*/
public interface AccountService extends IService<Account> {

    /**
     *  用于检查 校验 登入用户的 接口
     * @param username 登入名
     * @param password 登入密码
     * @return 登入成功 返回 状态码 200 以及 Account 对象 的 姓名
     *          || 登入失败 返回 状态码 -1 以及 错误提示
     */
    Result checkLoginAccount(String username, String password);
}
