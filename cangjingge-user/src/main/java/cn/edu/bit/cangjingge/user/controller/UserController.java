package cn.edu.bit.cangjingge.user.controller;

import cn.edu.bit.cangjingge.common.response.Response;
import cn.edu.bit.cangjingge.common.response.ResponseUtil;
import cn.edu.bit.cangjingge.user.entity.User;
import cn.edu.bit.cangjingge.user.service.SmsCodeManager;
import cn.edu.bit.cangjingge.user.service.UserServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private UserServiceImpl userService;

    @Resource
    private SmsCodeManager smsCodeManager;

    @ApiOperation("使用用户ID获取用户基本信息")
    @GetMapping("/id/{id}")
    public Response<User> getById(
            @PathVariable("id") final Long id
    ) {
        User user = userService.getById(id);
        return ResponseUtil.success(user);
    }

    @ApiOperation("使用用户名及密码创建安全信息")
    @PostMapping("/")
    public Response<User> register(
            @RequestParam("username") final String username,
            @RequestParam("password") final String password,
            @RequestParam("smsCode") final String smsCode
    ) {
        smsCodeManager.checkSmsCode(username, smsCode);
        User user = userService.register(username, password);
        return ResponseUtil.success(user);
    }

}
