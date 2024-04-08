package com.dingtalk.controller;

import com.dingtalk.model.Department;
import com.dingtalk.model.Role;
import com.dingtalk.model.ServiceResult;
import com.dingtalk.model.User;
import com.dingtalk.service.DingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 同步控制器
 *
 * @date 2021/12/12
 */
@RestController
@RequestMapping("/sync")
public class SyncController {

    @Autowired
    DingService dingService;

    @PostMapping("/getRoleList")
    public ServiceResult getRoleList() {
        List<Role> list =  dingService.getRoleList();
        return ServiceResult.getSuccessResult(list);
    }

    @PostMapping("/getUserList")
    public ServiceResult getUserList() {
        List<User> list =  dingService.getUserList("1");
        return ServiceResult.getSuccessResult(list);
    }

    @PostMapping("/getDepartmentList")
    public ServiceResult getDepartmentList() {
        List<Department> list =  dingService.getDepartMentList("1");
        return ServiceResult.getSuccessResult(list);
    }

    @PostMapping("/sync")
    public ServiceResult sync() {
        dingService.sync();
        return ServiceResult.getSuccessResult(200);
    }

}
