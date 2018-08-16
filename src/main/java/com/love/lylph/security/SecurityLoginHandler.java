package com.love.lylph.security;

import com.love.lylph.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 潘淮  on 2018/8/16.<br>
 */
@Component
public class SecurityLoginHandler implements AuthenticationSuccessHandler {

    private final Logger logger = LoggerFactory.getLogger(SecurityLoginHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        logger.info("登录成功-----------");
        User user = (User) authentication.getPrincipal();


    }
}
