package com.eekj.health.oauth.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.eekj.health.oauth.mapper.TbRoleMapper;
import com.eekj.health.oauth.service.TbRoleService;
@Service
public class TbRoleServiceImpl implements TbRoleService{

    @Resource
    private TbRoleMapper tbRoleMapper;

}
