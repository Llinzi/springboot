package com.eekj.health.oauth.service;

import com.eekj.health.oauth.entity.TbPermissionEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbPermissionService{

    List<TbPermissionEntity> selectByUserId(@Param("id") Long id);

}
