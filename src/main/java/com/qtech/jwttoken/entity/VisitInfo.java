package com.qtech.jwttoken.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 访客信息实体类
 * @Date: 2020/1/31 13:59
 * @Version: 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitInfo {
    private String id;
    private String visitLeavel;
    private String visitDept;
    private String visitTime;
    private String receptionist;
    private String receptionDept;
    private String escortLeaders;
    private String processPlan;
    private String remark;
    private String passenger;
    private int week;
    private String mornOrAfter;
    private String updateTime;
}
