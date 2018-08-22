package com.love.lylph.configuration;

import com.love.lylph.common.MD5;
import com.love.lylph.security.SecurityLoginHandler;
import com.love.lylph.security.SecurityProvider;
import com.love.lylph.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 * Created by 潘淮  on 2018/8/16.<br>
 */

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * SecurityService，从数据库中读取用户信息
     */
    @Autowired
    private SecurityService securityService;
    @Autowired
    private SecurityProvider securityProvider;
    @Autowired
    private SecurityLoginHandler securityLoginHandler;

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * 任何人都能访问： /login  /logout
         */
        http.authorizeRequests().antMatchers("/suu").permitAll() //都可以访问
                .anyRequest().authenticated()   // 任何请求，登录后都可以访问
                .and()
                .logout().logoutUrl("/logput").permitAll();
      //  http.formLogin().loginPage("/login").permitAll().and().authorizeRequests().anyRequest().authenticated();

       /* http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/lizi", "/sss")
                .permitAll()
                .anyRequest()
                .authenticated()
                .antMatchers("/home")
                .hasAuthority("ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .successHandler(securityLoginHandler)
                .and()
                .logout()
                .logoutUrl("/logout")
                .permitAll()
                .invalidateHttpSession(true)
                .and()
                .rememberMe()//登录后记住用户，下次自动登录,数据库中必须存在名为persistent_logins的表
                .tokenValiditySeconds(86000);*/
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(securityProvider).userDetailsService(securityService).passwordEncoder(passwordEncoder());
    }

    /**
     * 设置用户密码的加密方式为MD5加密
     * @return
     */
    @Bean
    public MD5 passwordEncoder() {
        return new MD5();
    }




}
