package com.eekj.health.oauth.mapper;

import com.eekj.health.oauth.entity.TbPermissionEntity;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TbPermissionMapper extends Mapper<TbPermissionEntity> {
    List<TbPermissionEntity> selectByUserId(@Param("id") Long id);
}