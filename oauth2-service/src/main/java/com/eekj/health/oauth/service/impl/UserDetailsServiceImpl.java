package com.eekj.health.oauth.service.impl;

import com.eekj.health.oauth.entity.TbPermissionEntity;
import com.eekj.health.oauth.entity.TbUserEntity;
import com.eekj.health.oauth.service.TbPermissionService;
import com.eekj.health.oauth.service.TbUserService;
import com.google.common.collect.Lists;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 自定义授权
 * @author: linzi
 * @create: 2020-07-15 14:27
 **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private TbUserService tbUserService;

    @Resource
    private TbPermissionService tbPermissionService;

    /**
     * 通过用户名查询到用户信息，然后查询用户所拥有的权限
     * @param s
     * @return
     * @throws UsernameNotFoundException
     */
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        
        return null;
    }
}
