package com.love.lylph.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.love.lylph.pojo.User;
import com.love.lylph.response.ResponseInfo;
import com.love.lylph.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author PanHuai
 * @data 2018/7/31 10:37
 */
@RestController
public class LoginController {

    @Autowired
    private UserService userService;


    /**
     * 发送验证码
     */


    /**
     * 图片验证
     */


    /**
     * 登录
     */
    @PostMapping("/login")
    public ResponseInfo postLogin(@RequestBody(required = false) Map<String,Object> params, HttpServletRequest request) throws JsonProcessingException {
        JsonObject result = new JsonObject();
        ObjectMapper mapper = new ObjectMapper();

        ResponseInfo responseInfo = new ResponseInfo();
        User user = userService.getUser((String) params.get("username"),(String) params.get("password"));
        if (user != null) {
            responseInfo.setCode(200);
            responseInfo.setMsg("成功");
            result.addProperty("username",user.getUsername());
            result.addProperty("password",user.getPassword());
        }else {
            responseInfo.setCode(201);
            responseInfo.setMsg("失败");
        }
        responseInfo.setData(result.toString());
        return  responseInfo;
    }

    /**
     * 更换密码
     */

    /**
     * 忘记密码
     */

    /**
     * 退出
     */
}
