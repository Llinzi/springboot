package com.eekj.health.oauth.service.impl;

import com.eekj.health.oauth.entity.TbPermissionEntity;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.eekj.health.oauth.mapper.TbPermissionMapper;
import com.eekj.health.oauth.service.TbPermissionService;

import java.util.List;

@Service
public class TbPermissionServiceImpl implements TbPermissionService{

    @Resource
    private TbPermissionMapper tbPermissionMapper;

    public List<TbPermissionEntity> selectByUserId(Long id) {
        return tbPermissionMapper.selectByUserId(id);
    }
}
