package com.dingtalk.service.impl;

import com.dingtalk.constant.UrlConstant;
import com.dingtalk.model.*;
import com.dingtalk.service.*;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;
import com.taobao.api.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.dingtalk.util.AccessTokenUtil.getAccessToken;


@Slf4j
@Service
public class DingServiceImpl implements DingService {


    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserDepartmentService userDepartmentService;

    @Override
    public List<User> getUserList(String dePartmentId) {
        List<User> users = getDTUserList(dePartmentId).getResult().getList().stream().map( user -> new User(
                user.getUserid(),
                user.getName(),
                user.getEmail(),
                user.getMobile(),
                user.getAvatar(),
                user.getStateCode()
        ) ).collect(Collectors.toList());
        return users;
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
                log.info("getDTRoleList错误原因:" + response.getErrmsg());
            }
        } catch (ApiException e) {
            log.info("获取token错误:" + e.getErrMsg());
        }
        return response;
    }

    @Override
    public OapiV2DepartmentListsubResponse getDTDepartMent(String department) {
        String token = getAccessToken();

        DingTalkClient client = new DefaultDingTalkClient(UrlConstant.GET_DEPARTMENT_LIST_URL);
        OapiV2DepartmentListsubRequest req = new OapiV2DepartmentListsubRequest();
        req.setDeptId(Long.valueOf(Long.valueOf(department)));
        OapiV2DepartmentListsubResponse response = null;
        try {
            response = client.execute(req, token);
            if (response.getErrcode() == 0) {
                return response;
            } else {
                log.info("getDTDepartMent错误原因:" + response.getErrmsg());
            }
        } catch (ApiException e) {
            log.info("获取token错误:" + e.getErrMsg());
        }

        return response;
    }



    @Override
    public OapiV2UserListResponse getDTUserList(String departmentId) {
        String token = getAccessToken();
        DingTalkClient client = new DefaultDingTalkClient(UrlConstant.GET_USER_LIST_URL);
        OapiV2UserListRequest req = new OapiV2UserListRequest();
        req.setDeptId(Long.valueOf(departmentId));
        req.setCursor(0L);
        req.setSize(100L);
        OapiV2UserListResponse response = null;
        try {
            response = client.execute(req, token);
            if (response.getErrcode() == 0) {
                return response;
            } else {
                log.info("getDTUserList错误原因:" + response.getErrmsg());
            }
        } catch (ApiException e) {
            log.info("获取token错误:" + e.getErrMsg());
        }
        return response;
    }

    public OapiUserListidResponse getUserIdsByDepartmentId(Department department) {
        String token = getAccessToken();
        DingTalkClient client = new DefaultDingTalkClient(UrlConstant.GET_USERIDS_BY_DEPARTMENTID);
        OapiUserListidRequest req = new OapiUserListidRequest();
        req.setDeptId(Long.valueOf(department.getDepartmentId()));
        req.putHeaderParam("access_token",token);
        OapiUserListidResponse response = null;
        try {
            response = client.execute(req, token);
            if (response.getErrcode() == 0) {
                return response;
            } else {
                log.info("getUserIdsByDepartmentId错误原因:" + response.getErrmsg());
            }
        } catch (ApiException e) {
            log.info("获取token错误:" + e.getErrMsg());
        }
        return response;
    }

    public OapiV2UserGetResponse getUserInfoById(String userid) {
        String token = getAccessToken();
        DingTalkClient client = new DefaultDingTalkClient(UrlConstant.GET_USERINFO_BY_ID);
        OapiV2UserGetRequest req = new OapiV2UserGetRequest();
        req.setUserid(userid);
        req.putHeaderParam("access_token",token);
        OapiV2UserGetResponse response = null;
        try {
            response = client.execute(req, token);
            if (response.getErrcode() == 0) {
                return response;
            } else {
                log.info("getUserInfoById错误原因:" + response.getErrmsg());
            }
        } catch (ApiException e) {
            log.info("获取token错误:" + e.getErrMsg());
        }
        return response;
    }


    /**
     * c_role
     * @return
     */
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
                    groupRoleList.getId().toString(),
                    groupRoleList.getName(),
                    openRoleGroup.getGroupId().toString(),
                    openRoleGroup.getName()
            ) ).collect(Collectors.toList());
            roleList.addAll(roleListAdd);
            roleListAdd.clear();
        }
        return roleList;
    }


    /**
     * c_department
     * @param departmentId
     * @return
     */
    @Override
    public List<Department> getDepartMentList(String departmentId) {
        List<Department> departmentListAll = new ArrayList<>();
        // 获取当前部门的子部门
        OapiV2DepartmentListsubResponse response = getDTDepartMent(departmentId.toString());
        if (response.getResult() == null || response.getResult().size()==0) {
            return departmentListAll; // 返回空列表而不是null
        }
        // 将子部门添加到总列表中
        List<Department> departmentListAdd = response.getResult().stream()
                .map(responseResult -> new Department(
                        responseResult.getDeptId().toString(),
                        responseResult.getParentId().toString(),
                        responseResult.getName()
                )).collect(Collectors.toList());
        departmentListAll.addAll(departmentListAdd);

        // 对每个子部门递归调用此方法，获取其子部门
        for (Department subDept : departmentListAdd) {
            List<Department> subDepartments = getDepartMentList(subDept.getDepartmentId());
            if (subDepartments != null) {
                departmentListAll.addAll(subDepartments);
            }
        }
        return departmentListAll;
    }

    /**
     * 用户元数据同步
     * c_user
     * c_user_department
     * c_user_role
     */
    @Override
    public void sync() {
        String token = getAccessToken();
        List<Role> roles = getRoleList();
        //可以先调用获取部门列表接口(https://oapi.dingtalk.com/topapi/v2/department/listsub)，获取到该企业下所有部门ID。
        List<Department> departments = getDepartMentList("1");
        //根据部门id查询部门下所有的用户id列表(https://oapi.dingtalk.com/topapi/user/listid)
        List<String> userIds = new ArrayList<>();
        List<String> userIdsAdd = new ArrayList<>();
        for(Department department : departments){
            OapiUserListidResponse response = getUserIdsByDepartmentId(department);
            if(response.getResult() != null){
                userIdsAdd = response.getResult().getUseridList();
            }
            userIds.addAll(userIdsAdd);
        }
        //遍历调用获取部门用户userid列表接口(https://oapi.dingtalk.com/topapi/v2/user/get)。
        List<User> users = new ArrayList<>();
        List<UserRole> userRoles = new ArrayList<>();
        List<UserDepartment> userDepartments = new ArrayList<>();
        for (String id : userIds){
            User user = new User();
            List<UserRole> userRolesAdd = new ArrayList<>();
            List<UserDepartment> userDepartmentsAdd = new ArrayList<>();
            OapiV2UserGetResponse response = getUserInfoById(id);
            if(response != null){
                user.setUserId(response.getResult().getUserid());
                user.setUsername(response.getResult().getName());
                user.setMobile(response.getResult().getMobile());
                user.setEmail(response.getResult().getEmail());
                user.setAvatar(response.getResult().getAvatar());
                user.setStatus(response.getResult().getStateCode());
                for (OapiV2UserGetResponse.UserRole role: response.getResult().getRoleList()) {
                    userRolesAdd.add(new UserRole(id, String.valueOf(role.getId())));
                }
                for (OapiV2UserGetResponse.DeptLeader deptLeader: response.getResult().getLeaderInDept()) {
                    userDepartmentsAdd.add(new UserDepartment(id,String.valueOf((deptLeader.getDeptId())),deptLeader.getLeader()));
                }
            }
            users.add(user);
            userRoles.addAll(userRolesAdd);
            userDepartments.addAll(userDepartmentsAdd);
        }
        System.out.println("打印 roles ===");
        roles.stream().forEach(role -> System.out.println(role.toString()));
        roleService.batchInsertRoles(roles);
        System.out.println("打印 departments ===");
        departments.stream().forEach(department -> System.out.println(department.toString()));
        departmentService.batchInsertDepartments(departments);
        System.out.println("打印 users ===");
        users.stream().forEach(user1 -> System.out.println(user1.toString()));
        userService.batchInsertUsers(users);
        System.out.println("打印 userRoles ===");
        userRoles.stream().forEach(userRole -> System.out.println(userRole.toString()));
        userRoleService.batchInsertUserRoles(userRoles);
        System.out.println("打印 userDepartments ===");
        userDepartments.stream().forEach(userDepartment -> System.out.println(userDepartment.toString()));
        userDepartmentService.batchInsertUserDepartments(userDepartments);
    }
}
