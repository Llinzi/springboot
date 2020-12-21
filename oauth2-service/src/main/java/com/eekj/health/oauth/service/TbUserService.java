package com.eekj.health.oauth.service;

import com.eekj.health.oauth.entity.TbUserEntity;

public interface TbUserService{

    /**
     * 通过用户 名称 查询用户信息
     * @param username
     * @return
     */
    TbUserEntity getByUserName(String username);

}
