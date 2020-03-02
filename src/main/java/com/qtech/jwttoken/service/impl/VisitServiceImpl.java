package com.qtech.jwttoken.service.impl;


import com.qtech.jwttoken.entity.VisitInfo;
import com.qtech.jwttoken.mapper.VisitInfoMapper;
import com.qtech.jwttoken.service.VisitService;
import com.qtech.jwttoken.utils.TimeTuils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description: 访问信息处理实现
 * @Date: 2020/1/31 14:07
 * @Version: 1.0
 */
@Service
public class VisitServiceImpl implements VisitService {
    Logger logger = LoggerFactory.getLogger(VisitServiceImpl.class);

    @Autowired
    VisitInfoMapper visitInfoMapper;

    @Override
    public Boolean saveVisitInfo(Map<String, Object> visitInfoMap) {
        VisitInfo visitInfo = new VisitInfo();
        try {
            String visitLeavel = String.valueOf(visitInfoMap.getOrDefault("visitLeavel","一般来访"));
            String visitTime = String.valueOf(visitInfoMap.getOrDefault("visitTime","未填写来访时间"));
            String receptionist = String.valueOf(visitInfoMap.getOrDefault("receptionist","无接待人员"));
            String receptionDept = String.valueOf(visitInfoMap.getOrDefault("receptionDept","无接待部门"));
            String processPlan = String.valueOf(visitInfoMap.getOrDefault("processPlan","无流程安排"));
            String remark = String.valueOf(visitInfoMap.getOrDefault("remark","暂无备注"));
            String passenger = String.valueOf(visitInfoMap.getOrDefault("passenger","无预订人"));
            String mornOrAfter= "不详";
            int week = -1;
            // 处理时间数据（获取周几&上午或者下午）
            week = TimeTuils.getWeek(visitTime);
            mornOrAfter = TimeTuils.getMornOrAfter(visitTime);



            // 获取来访部门
            List<LinkedHashMap> visitDepts = (List<LinkedHashMap>)visitInfoMap.get("visitDept");
            String visitDeptsStr = null;
            if (visitDepts != null){
                for (LinkedHashMap visitDept:visitDepts){
                    if (visitDeptsStr == null){
                        visitDeptsStr = String.valueOf(visitDept.get("deptName"));
                    }else {
                        visitDeptsStr = visitDeptsStr + "," +visitDept.get("deptName");
                    }
                }
            }else {
                visitDeptsStr = "来访部门未填";
            }
             // 获取陪同领导
            List<LinkedHashMap> escortLeaders = (List<LinkedHashMap>)visitInfoMap.get("escortLeaders");
            String escortLeadersStr = null;
            if (escortLeaders != null){
                for (LinkedHashMap escortLeader:escortLeaders){
                    if (escortLeadersStr == null){
                        escortLeadersStr = String.valueOf(escortLeader.get("leaderName"));
                    }else {
                        escortLeadersStr = escortLeadersStr + "," +escortLeader.get("leaderName");
                    }
                }
            }else {
                escortLeadersStr = "无陪同领导";
            }
            // 生成唯一id
            String uuid = UUID.randomUUID()+String.valueOf(visitTime.hashCode());
            visitInfo.setId(uuid);
            visitInfo.setVisitDept(visitDeptsStr);
            visitInfo.setEscortLeaders(escortLeadersStr);
            visitInfo.setVisitLeavel(visitLeavel);
            visitInfo.setVisitTime(visitTime);
            visitInfo.setReceptionist(receptionist);
            visitInfo.setReceptionDept(receptionDept);
            visitInfo.setProcessPlan(processPlan);
            visitInfo.setRemark(remark);
            visitInfo.setPassenger(passenger);
            visitInfo.setWeek(week);
            visitInfo.setMornOrAfter(mornOrAfter);
            System.out.println(visitInfo);
            visitInfoMapper.saveData(visitInfo);
            return true;
        } catch (Exception e) {
            logger.error("访客提交数据处理异常！！！");
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public List<VisitInfo> getAllData() {
        List<VisitInfo> allData = visitInfoMapper.getAllData();
        return allData;
    }

    @Override
    public List<VisitInfo> getOneWeekData() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        Date date = new Date();
        Calendar calender = Calendar.getInstance();
        calender.setTime(date);
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了  
        int dayWeek = calender.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天  
        if (1 == dayWeek) {
            calender.add(Calendar.DAY_OF_MONTH, -1);
        }
        // System.out.println("要计算日期为:" + sdf.format(calender.getTime())); // 输出要计算日期  
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一  
        calender.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天  
        int day = calender.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值  
        calender.add(Calendar.DATE, calender.getFirstDayOfWeek() - day);
        String imptimeBegin = sdf.format(calender.getTime());
        // System.out.println("所在周星期一的日期：" + imptimeBegin);  
        calender.add(Calendar.DATE, 7);
        String imptimeEnd = sdf.format(calender.getTime());
        // System.out.println("所在周星期日的日期：" + imptimeEnd);
        List<VisitInfo> oneWeekData = visitInfoMapper.getOneWeekData(imptimeBegin, imptimeEnd);





        return oneWeekData;
    }

    @Override
    public VisitInfo queryById(String id) {
        VisitInfo visitInfo = visitInfoMapper.queryById(id);
        return visitInfo;
    }
}
