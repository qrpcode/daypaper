package com.qiruipeng.sys.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiruipeng.sys.dao.SystemMapper;
import com.qiruipeng.sys.pojo.TextSet;
import com.qiruipeng.sys.service.SystemService;
import com.qiruipeng.sys.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统服务
 * @author Administrator
 */

@Service(interfaceClass = SystemService.class)
@Transactional
public class SystemServiceImpl implements SystemService {

    private final String SYSTEM_KEY = "system";

    @Autowired
    private RedisUtil redisUtil;
    /**
     *
     */
    @Autowired
    private SystemMapper systemMapper;

    @Override
    public TextSet getSystem() {
        TextSet system = null;
        if(redisUtil.hasRedis(SYSTEM_KEY)){
            system = JSONArray.parseObject(redisUtil.getRedis(SYSTEM_KEY), TextSet.class);
        }else{
            //查表
            system = systemMapper.selectByPrimaryKey("1");
            //储存redis
            redisUtil.saveRedis(SYSTEM_KEY, JSONObject.toJSONString(system));
        }
        return system;
    }
}
