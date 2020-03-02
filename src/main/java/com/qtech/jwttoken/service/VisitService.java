package com.qtech.jwttoken.service;

import com.qtech.jwttoken.entity.VisitInfo;

import java.util.List;
import java.util.Map;

/**
 * @Description: 访问信息处理接口定义
 * @Date: 2020/1/31 14:04
 * @Version: 1.0
 */
public interface VisitService {
    Boolean saveVisitInfo(Map<String,Object> visitInfoMap);
    List<VisitInfo> getAllData();
    List<VisitInfo> getOneWeekData();
    VisitInfo queryById(String id);
}
