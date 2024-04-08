package com.dingtalk.service;

import com.dingtalk.api.request.OapiRoleListRequest;
import com.dingtalk.model.Department;
import com.dingtalk.api.response.*;
import com.dingtalk.model.Role;
import com.dingtalk.model.User;

import java.util.List;
import java.util.Map;

public interface DingService {

    List<Department> getDepartMentList(Long dePartmentId);

    List<User> getUserList(Long dePartmentId);

    List<Role> getRoleList();

    OapiRoleListResponse getDTRoleList();

    OapiV2DepartmentListsubResponse getDTDepartMent(Department department);

    OapiV2UserListResponse getDTUserList(Department department);
}


