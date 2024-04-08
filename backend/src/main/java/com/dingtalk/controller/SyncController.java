package com.dingtalk.controller;

import com.dingtalk.model.Role;
import com.dingtalk.model.ServiceResult;
import com.dingtalk.service.DingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 同步控制器
 *
 * @date 2021/12/12
 */
@RestController
public class SyncController {

    @Autowired
    DingService dingService;

    @GetMapping("/getRoleList")
    public ServiceResult getRoleList() {
        List<Role> list =  dingService.getRoleList();
        return ServiceResult.getSuccessResult(list);
    }

}
