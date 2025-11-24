package com.mlm.mlmserver.controller;

import com.alibaba.fastjson2.JSONObject;
import com.mlm.mlmserver.entity.Response;
import com.mlm.mlmserver.service.MyDatabaseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/to/")
public class LoginController {
    @Resource
    private MyDatabaseService dbs;

    @PostMapping("login")
    public Response<Object> login(@RequestBody JSONObject obj) {
        String username = obj.getString("username");
        String password = obj.getString("password");
        // 校验
        JSONObject validate = new JSONObject();
        if (username == null || username.isEmpty()) {validate.put("username", "必须参数，且不能为空。");}
        if (password == null || password.isEmpty()) {validate.put("password", "必须参数，且不能为空。");}
        if (validate.isEmpty()) {
            JSONObject user = dbs.login("t_user", "username,type,head", username, password);
            return new Response<Object>().success(user);
        } else {
            return new Response<Object>().error(400, "参数错误", validate);
        }
    }
}
