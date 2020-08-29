package com.qiruipeng.sys.service;


import com.qiruipeng.sys.pojo.TextSet;

/**
 * @author Administrator
 */
public interface SystemService {
    /**
     * 获取系统信息，如果redis不存在就从数据库获取并储存redis
     * @return
     */
    TextSet getSystem();
}
