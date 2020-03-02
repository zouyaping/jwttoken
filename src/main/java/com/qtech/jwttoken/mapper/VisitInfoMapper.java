package com.qtech.jwttoken.mapper;

import com.qtech.jwttoken.entity.VisitInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 访客信息相关操作
 * @Date: 2020/1/31 17:45
 * @Version: 1.0
 */
@Repository
public interface VisitInfoMapper {
    Integer saveData(@Param("visitInfo") VisitInfo visitInfo);
    List<VisitInfo> getAllData();
    List<VisitInfo> getOneWeekData(String startTime, String endTime);
    VisitInfo queryById(String id);
}
