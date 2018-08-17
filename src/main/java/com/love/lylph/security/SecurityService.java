package com.love.lylph.security;

import com.love.lylph.pojo.User;
import com.love.lylph.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by 潘淮  on 2018/8/16.<br>
 */

@Component
public class SecurityService implements UserDetailsService{

    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.getUser(username, null);

        if (user == null) {
            throw new UsernameNotFoundException("UserName " + username + " not found");
        }

        UserDetails userDetails = new SecurityDto(user);
        return userDetails;
    }
}
