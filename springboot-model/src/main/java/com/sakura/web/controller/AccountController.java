package com.sakura.web.controller;

import com.sakura.web.common.Result;
import com.sakura.web.pojo.Account;
import com.sakura.web.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     *  注册用户功能
     * @param account 用户对象 信息集合
     * @return 结果信息
     */
    @RequestMapping("/registered")
    public Result doRegistered(@RequestBody Account account) {
        Result result = accountService.registeredAccount(account.getUserName(), account.getPassWord());

        return result;
    }

    /**
     * 登入功能的控制器
     * @param account 用户信息 对象
     * @return 返回 结果信息
     */
    @PostMapping("/login")
    public Result doLogin(@RequestBody Account account) {
        System.out.println(account);
        return accountService.checkLoginAccount(account.getUserName(), account.getPassWord());
    }
}
