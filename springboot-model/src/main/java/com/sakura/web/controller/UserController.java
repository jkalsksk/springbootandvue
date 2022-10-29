package com.sakura.web.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sakura.web.common.Result;
import com.sakura.web.pojo.User;
import com.sakura.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 根据 ID 删除 一个用户
     * @param id 前端传过来的 真实ID
     * @return 响应信息 以及 状态码
     */
    @DeleteMapping("/delete/{id}")
    @Transactional
    public Result doDelete(@PathVariable Long id) {
        boolean b = userService.removeById(id);

        if (b) {
            return Result.success();
        } else {
            return Result.error("202", "亲 id 不对哟!");
        }

    }


    /**
     * 根据 ID 修改用户信息
     * @param user 用户要修改的信息 实体
     * @return 响应结果 以及 状态码
     */
    @Transactional
    @PutMapping("/update")
    public Result doUpdate(@RequestBody User user) {
        boolean f = userService.updateUserById(user);

        if (f) {
            // 为 true 则 修改成功
            return Result.success();
        } else {
            // false 修改失败
            return Result.error("error", "亲 检查一下数据是否传输 错误");
        }
    }


    /**
     * 新增用户
     * @param user 前端传 过来的 用户实体 对象
     * @return 状态码 以及 响应信息
     */
    @Transactional
    @PostMapping("/save")
    public Result<?> save(@RequestBody User user) {
        if (null == user.getNickName() || "".equals(user.getNickName())) {
            // 如果没有 传送过来 对象 就返回 错误信息
            return Result.error("400", "请输入昵称!");
        }

        // 判断 是否 昵称冲突
        if (userService.checkNickName(user.getNickName())) {
            // 如果 返回的 结果 为true 则 说明存在 相同昵称的 用户 无法进行存储
            return Result.error("202", "亲 该昵称以被 占用哦 请取其他的昵称!");
        }

        // 设置默认密码
        if (user.getPassword() == null || "".equals(user.getPassword())) {
            user.setPassword("123456");
        }

        // 昵称不为 null 时 就保存 用户信息
        boolean save = userService.save(user);

        if (save) {
            return Result.success();
        } else
            return Result.error("500", "服务器内存压力大 请稍后尝试!");
    }

    /**
     * 分页查询 所有数据
     * @param currentPage 当前页码
     * @param pageSize 要分页查询的 大小
     * @param search 模糊查询的关键字
     * @return 结果集 以及 状态码
     */
    @GetMapping
    public Result<?> doList(@RequestParam(name = "pageNum") Integer currentPage,
                            @RequestParam("pageSize") Integer pageSize,
                            @RequestParam("search") String search) {

        Page<User> userPage = userService.listPage(currentPage, pageSize, search);

        return Result.success(userPage);
    }

}
