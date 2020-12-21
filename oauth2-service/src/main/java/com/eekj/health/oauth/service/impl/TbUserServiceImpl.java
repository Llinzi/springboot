package com.eekj.health.oauth.service.impl;

import com.eekj.health.oauth.entity.TbUserEntity;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.eekj.health.oauth.mapper.TbUserMapper;
import com.eekj.health.oauth.service.TbUserService;
import tk.mybatis.mapper.entity.Example;

@Service
public class TbUserServiceImpl {

    @Resource
    private TbUserMapper tbUserMapper;

    public TbUserEntity getByUserName(String username) {
        Example example = new Example(TbUserEntity.class);
        example.createCriteria().andEqualTo("username",username);
        return tbUserMapper.selectOneByExample(example);
    }
}
