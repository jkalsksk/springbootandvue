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
     * 登入功能的控制器
     * @param username 登入名
     * @param password 登入密码
     * @return 返回 结果信息
     */
    @PostMapping("/login")
    public Result doLogin(@RequestBody Account account) {
        System.out.println(account);
        return accountService.checkLoginAccount(account.getUserName(), account.getPassWord());
    }
}
