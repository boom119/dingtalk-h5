package com.dingtalk.service.impl;

import com.dingtalk.constant.UrlConstant;
import com.dingtalk.model.Department;
import com.dingtalk.model.Role;
import com.dingtalk.model.User;
import com.dingtalk.service.DingService;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;
import com.taobao.api.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.dingtalk.util.AccessTokenUtil.getAccessToken;


@Slf4j
@Service
public class DingServiceImpl implements DingService {


    @Override
    public List<Department> getDepartMentList(Long dePartmentId) {

        List<Department> departmentListAll =  new ArrayList<>();
        List<Department> departmentListAdd;
        OapiV2DepartmentListsubResponse response;
        Department department = new Department();
        department.setDepartment_id(dePartmentId);
        response = getDTDepartMent(department);
        if (response.getResult() == null || response.getResult().isEmpty()) {
            return null;
        }
        // 将子部门添加到总列表中
         departmentListAdd = response.getResult().stream().map( responseResult ->  new Department(
                responseResult.getDeptId(),
                responseResult.getParentId(),
                responseResult.getName()
        )).collect(Collectors.toList());
        departmentListAll.addAll(departmentListAdd);

        // 对每个子部门递归调用此方法，获取其子部门
        for (Department subDept : departmentListAdd) {
            getDepartMentList(subDept.getDepartment_id());
        }
        return departmentListAll;

    }

    @Override
    public List<User> getUserList(Long dePartmentId) {
        Department department = new Department();
        department.setDepartment_id(dePartmentId);
        return getUserList(dePartmentId);
    }

    @Override
    public List<Role> getRoleList() {
        List<Role> roleList = new ArrayList<>();
        OapiRoleListResponse response = getDTRoleList();
        if(response.getResult()== null || response.getResult().getList().isEmpty()){
            return null;
        }
        List<OapiRoleListResponse.OpenRoleGroup> list = response.getResult().getList();
        List<OapiRoleListResponse.OpenRole> roles = new ArrayList<>();
        for(OapiRoleListResponse.OpenRoleGroup openRoleGroup :  list ){
            List<Role> roleListAdd = new ArrayList<>();
            roleListAdd = openRoleGroup.getRoles().stream().map( groupRoleList -> new Role(
                    groupRoleList.getId(),
                    groupRoleList.getName()
            ) ).collect(Collectors.toList());
            roleList.addAll(roleListAdd);
            roleListAdd.clear();
        }
        return roleList;
    }

    @Override
    public OapiRoleListResponse getDTRoleList() {
        String token = getAccessToken();
        DingTalkClient client = new DefaultDingTalkClient(UrlConstant.GET_ROLE_LIST_URL);
        OapiRoleListRequest req = new OapiRoleListRequest();
        req.setSize(100L);
        OapiRoleListResponse response = null;
        try {
            response = client.execute(req, token);
            if (response.getErrcode() == 0) {
                return response;
            } else {
                log.info("getDepartMent错误原因:" + response.getErrmsg());
            }
        } catch (ApiException e) {
            log.info("获取token错误:" + e.getErrMsg());
        }
        return response;
    }

    @Override
    public OapiV2DepartmentListsubResponse getDTDepartMent(Department department) {
        String token = getAccessToken();

        DingTalkClient client = new DefaultDingTalkClient(UrlConstant.GET_DEPARTMENT_LIST_URL);
        OapiV2DepartmentListsubRequest req = new OapiV2DepartmentListsubRequest();
        req.setDeptId(1L);
        OapiV2DepartmentListsubResponse response = null;
        try {
            response = client.execute(req, token);
            if (response.getErrcode() == 0) {
                return response;
            } else {
                log.info("getDepartMent错误原因:" + response.getErrmsg());
            }
        } catch (ApiException e) {
            log.info("获取token错误:" + e.getErrMsg());
        }

        return response;
    }



    @Override
    public OapiV2UserListResponse getDTUserList(Department department) {
        String token = getAccessToken();
        DingTalkClient client = new DefaultDingTalkClient(UrlConstant.GET_USER_LIST_URL);
        OapiV2UserListRequest req = new OapiV2UserListRequest();
        req.setDeptId(department.getDepartment_id());
        OapiV2UserListResponse response = null;
        try {
            response = client.execute(req, token);
            if (response.getErrcode() == 0) {
                return response;
            } else {
                log.info("getDepartMent错误原因:" + response.getErrmsg());
            }
        } catch (ApiException e) {
            log.info("获取token错误:" + e.getErrMsg());
        }
        return response;
    }

}
