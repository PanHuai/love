package com.love.lylph.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.love.lylph.common.JsonUtils;
import com.love.lylph.common.MD5;
import com.love.lylph.pojo.User;
import com.love.lylph.response.ResponseInfo;
import com.love.lylph.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * @author PanHuai
 * @data 2018/7/31 10:37
 */
@RestController
@CacheConfig(cacheNames = "login")
public class LoginController {

    @Autowired
    private UserService userService;


    /**
     * 发送验证码
     */
    @PreAuthorize("hasAnyRole('user','admin','pass')")
    @RequestMapping("/logins")
    public ResponseInfo login() {
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setCode(200);
        responseInfo.setMsg("成功");
        User user = new User();
        user.setName("ser");
        JsonObject js = new JsonObject();
        js.addProperty("user",JsonUtils.getJsonUtils().toJson(user));
        responseInfo.setData(js.toString());
        return responseInfo;
    }
    /**
     * 图片验证
     */

    /**
     * 注册
     * ROLE_admin
     */
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public ResponseInfo toRegister(@RequestBody()Map<String, String> params,HttpServletRequest request) {

        ResponseInfo response = new ResponseInfo();

        String username = params.get("username");
        String password = params.get("password");
        User userByUserName = userService.findUserByUserName(username);
        if (userByUserName != null) {
            response.setCode(400);
            response.setMsg("改账号已存在");
        } else {
            User user = new User();
            user.setUsername(username);
            String s = MD5.encodeMd5(password);
            user.setPassword(s);
            user.setEnable(true);
            user.setRolename("ROLE_pt");
            user.setCreatetime(new Date());
            int insert = userService.insert(user);
            if (insert > 0) {
                response.setCode(200);
                response.setMsg("账号注册成功");
            }
        }
        return response;
    }

    /**
     * 登录
     */
/*    @Cacheable
    @PreAuthorize("hasAnyRole('user','admin','pt')")
    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "params", value = "用户详细实体user", required = true, dataType = "Map")
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ResponseInfo postLogin(@RequestBody() Map<String,String> params, HttpServletRequest request) throws JsonProcessingException {
        JsonObject result = new JsonObject();
        ObjectMapper mapper = new ObjectMapper();
        ResponseInfo responseInfo = new ResponseInfo();
        User user = userService.findUserByUserName(params.get("username"));
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
    }*/
    @PreAuthorize("hasAuthority('user')")
    @RequestMapping("/login")
    public ResponseInfo login(HttpServletRequest request) {
        ResponseInfo response = new ResponseInfo();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User userByUserName = userService.findUserByUserName(username);
        if (userByUserName == null) {
            response.setCode(404);
            response.setMsg("不存在");
        } else {
            response.setCode(200);
            response.setMsg("成功");
        }
        return response;
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
