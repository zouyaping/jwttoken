package com.qtech.jwttoken.controller;

import com.qtech.jwttoken.entity.VisitInfo;
import com.qtech.jwttoken.service.VisitService;
import com.qtech.jwttoken.utils.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Description: 访客信息处理controller层
 * @Date: 2020/1/31 14:24
 * @Version: 1.0
 */

@RestController
@RequestMapping(value = "/visitInfo")
public class VisitInfoController {

    @Autowired
    VisitService visitService;

    // 保存访客数据
    @PostMapping(value = "/saveData")
    public Result saveData(@RequestBody Map<String,Object> visitInfoMap){
        Boolean result = visitService.saveVisitInfo(visitInfoMap);
        if (result){
            return Result.successWithMsg("保存数据成功");
        }else {
            return Result.error("保存数据失败");
        }
    }
    // 取所有访客数据
    @GetMapping(value = "/getAllData")
    public Result getAllData(){
        List<VisitInfo> allData = visitService.getAllData();
        if (allData != null){
            return Result.successWithData(allData);
        }else {
            return Result.error("获取数据失败");
        }
    }
    // 获取一周访客数据
    @GetMapping(value = "/getOneWeekData")
    public Result getOneWeekData(){
        List<VisitInfo> oneWeekData = visitService.getOneWeekData();
        if (oneWeekData != null){
            return Result.successWithData(oneWeekData);
        }else {
            return Result.error("获取数据失败");
        }
    }
    //根据id查询访问记录
    @GetMapping(value = "/queryById")
    public Result queryById(@Param("id") String id){
        VisitInfo visitInfo = visitService.queryById(id);
        if (visitInfo != null){
            return Result.successWithData(visitInfo);
        }else {
            return Result.error("查询单条记录失败！！！");
        }
    }
}
