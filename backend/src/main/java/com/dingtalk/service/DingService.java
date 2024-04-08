package com.dingtalk.service;

import com.dingtalk.api.request.OapiRoleListRequest;
import com.dingtalk.model.Department;
import com.dingtalk.api.response.*;
import com.dingtalk.model.Role;
import com.dingtalk.model.User;

import java.util.List;
import java.util.Map;

public interface DingService {


    OapiRoleListResponse getDTRoleList();

    OapiV2DepartmentListsubResponse getDTDepartMent(String department);

    OapiV2UserListResponse getDTUserList(String department);

    void sync();

    List<Department> getDepartMentList(String dePartmentId);

    List<User> getUserList(String dePartmentId);

    List<Role> getRoleList();
}


