package com.eekj.health.oauth.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.eekj.health.oauth.mapper.TbContentMapper;
import com.eekj.health.oauth.service.TbContentService;
@Service
public class TbContentServiceImpl implements TbContentService{

    @Resource
    private TbContentMapper tbContentMapper;

}
