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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
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
        List<OapiV2UserListResponse.ListUserResponse> listUserResponses = getAllDTUserList(dePartmentId);
        List<User> users = listUserResponses.stream().map(user -> new User(
                user.getUserid(),
                user.getName(),
                user.getEmail(),
                user.getMobile(),
                user.getAvatar(),
                user.getStateCode()
        )).collect(Collectors.toList());
        return users;
    }


    @Override
    public OapiRoleListResponse getDTRoleList() {
        String token = getAccessToken();
        DingTalkClient client = new DefaultDingTalkClient(UrlConstant.GET_ROLE_LIST_URL);
        OapiRoleListRequest req = new OapiRoleListRequest();
//        req.setSize(100L);
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

    public List<OapiV2UserListResponse.ListUserResponse> getAllDTUserList(String departmentId) {
        String token = getAccessToken();
        DingTalkClient client = new DefaultDingTalkClient(UrlConstant.GET_USER_LIST_URL);
        List<OapiV2UserListResponse.ListUserResponse> allUsers = new ArrayList<>();

        long cursor = 0L;
        long size = 100L; // 可以设置为API支持的最大值
        boolean hasMore = true;

        while (hasMore) {
            OapiV2UserListRequest req = new OapiV2UserListRequest();
            req.setDeptId(Long.valueOf(departmentId));
            req.setCursor(cursor);
            req.setSize(size);

            try {
                OapiV2UserListResponse response = client.execute(req, token);
                if (response.getErrcode() == 0) {
                    // 将获取到的用户列表加入到总列表中
                    allUsers.addAll(response.getResult().getList());
                    // 更新hasMore和cursor，为下一次请求准备
                    hasMore = response.getResult().getHasMore();
                    cursor += size; // 或者你可以使用response.getResult().getNextCursor()来获取下一个游标，如果API提供了这样的字段
                } else {
                    log.info("getDTUserList错误原因:" + response.getErrmsg());
                    break; // 发生错误时退出循环
                }
            } catch (ApiException e) {
                log.info("调用钉钉API发生错误:" + e.getErrMsg());
                break; // 发生异常时退出循环
            }
        }
        return allUsers;
    }

    public OapiUserListidResponse getUserIdsByDepartmentId(Department department) {
        String token = getAccessToken();
        DingTalkClient client = new DefaultDingTalkClient(UrlConstant.GET_USERIDS_BY_DEPARTMENTID);
        OapiUserListidRequest req = new OapiUserListidRequest();
        req.setDeptId(Long.valueOf(department.getDepartmentId()));
        req.putHeaderParam("access_token", token);
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
        req.putHeaderParam("access_token", token);
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
     *
     * @return
     */
    @Override
    public List<Role> getRoleList() {
        List<Role> roleList = new ArrayList<>();
        OapiRoleListResponse response = getDTRoleList();
        if (response.getResult() == null || response.getResult().getList().isEmpty()) {
            return null;
        }
        List<OapiRoleListResponse.OpenRoleGroup> list = response.getResult().getList();
        List<OapiRoleListResponse.OpenRole> roles = new ArrayList<>();
        for (OapiRoleListResponse.OpenRoleGroup openRoleGroup : list) {
            List<Role> roleListAdd = new ArrayList<>();
            roleListAdd = openRoleGroup.getRoles().stream().map(groupRoleList -> new Role(
                    groupRoleList.getId().toString(),
                    groupRoleList.getName(),
                    openRoleGroup.getGroupId().toString(),
                    openRoleGroup.getName()
            )).collect(Collectors.toList());
            roleList.addAll(roleListAdd);
            roleListAdd.clear();
        }
        return roleList;
    }


    /**
     * c_department
     *
     * @param departmentId
     * @return
     */
    @Override
    public List<Department> getDepartMentList(String departmentId) {
        List<Department> departmentListAll = new ArrayList<>();
        // 获取当前部门的子部门
        OapiV2DepartmentListsubResponse response = getDTDepartMent(departmentId.toString());
        if (response.getResult() == null || response.getResult().size() == 0) {
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
        for (Department department : departments) {
            OapiUserListidResponse response = getUserIdsByDepartmentId(department);
            if (response.getResult() != null) {
                userIdsAdd = response.getResult().getUseridList();
            }
            userIds.addAll(userIdsAdd);
        }
        //遍历调用获取部门用户userid列表接口(https://oapi.dingtalk.com/topapi/v2/user/get)。
        List<User> users = new ArrayList<>();
        List<UserRole> userRoles = new ArrayList<>();
        List<UserDepartment> userDepartments = new ArrayList<>();
        for (String id : userIds) {
            User user = new User();
            List<UserRole> userRolesAdd = new ArrayList<>();
            List<UserDepartment> userDepartmentsAdd = new ArrayList<>();
            OapiV2UserGetResponse response = getUserInfoById(id);
            if (response != null) {
                user.setUserId(response.getResult().getUserid());
                user.setUsername(response.getResult().getName());
                user.setMobile(response.getResult().getMobile());
                user.setEmail(response.getResult().getEmail());
                user.setAvatar(response.getResult().getAvatar());
                user.setStatus(response.getResult().getStateCode());
                for (OapiV2UserGetResponse.UserRole role : response.getResult().getRoleList()) {
                    userRolesAdd.add(new UserRole(id, String.valueOf(role.getId())));
                }
                for (OapiV2UserGetResponse.DeptLeader deptLeader : response.getResult().getLeaderInDept()) {
                    userDepartmentsAdd.add(new UserDepartment(id, String.valueOf((deptLeader.getDeptId())), deptLeader.getLeader()));
                }
            }
            users.add(user);
            userRoles.addAll(userRolesAdd);
            userDepartments.addAll(userDepartmentsAdd);
        }
        //插入角色表
        syncRoles(roles);
        //更新部门表
        syncDepartments(departments);
        //更新用户表
        syncUsers(users);
        //更新用户角色关联表
        syncUserRoles(userRoles);
        //更新用户部门关联表
        syncUserDepartments(userDepartments);
    }

    public void syncUsers(List<User> users) {
        List<User> usersFromDb = userService.selectAllUsers();
        // 将数据库用户列表转换为Map，以便快速按id查询
        Map<String, User> userDbMap = usersFromDb.stream()
                .collect(Collectors.toMap(User::getUserId, user -> user));

        // 处理钉钉上的用户列表
        for (User userFromDingTalk : users) {
            User userFromDb = userDbMap.get(userFromDingTalk.getUserId());

            if (userFromDb == null) {
                // 如果数据库中没有，插入新用户
                System.out.println("需要新增的User："+userFromDingTalk.toString());
                userService.insertUser(userFromDingTalk);
            } else {
                // 检查用户信息是否需要更新
                if (!userFromDb.equals(userFromDingTalk)) {
                    System.out.println("需要更新的User："+userFromDingTalk.toString());
                    userService.updateUser(userFromDingTalk);
                }
                // 用户已处理，从映射中移除
                userDbMap.remove(userFromDingTalk.getUserId());
            }
        }

        // 用户DbMap中剩余的用户在钉钉上不存在，应被删除
        for (String userId : userDbMap.keySet()) {
            System.out.println("需要刪除的User："+userId);
            userService.deleteUserById(userId);
        }
    }

    public void syncRoles(List<Role> roles) {
        // 首先，我们把数据库中的角色转换为映射，以角色ID作为键：
        List<Role> rolesDB = roleService.selectAllRoles();
        Map<String, Role> roleDbMap = rolesDB.stream()
                .collect(Collectors.toMap(Role::getRoleId, Function.identity()));

        // 现在我们遍历钉钉角色列表：
        for (Role roleFromDingTalk : roles) {
            Role matchingRoleFromDb = roleDbMap.get(roleFromDingTalk.getRoleId());

            if (matchingRoleFromDb != null) {
                // 如果在数据库中找到了角色，则检查是否需要更新:
                if (!roleFromDingTalk.equals(matchingRoleFromDb)) {
                    // 这里应该有一个方法来决定哪些字段需要进行对比和更新，
                    // 可以是一个简单的.equals方法，也可以是更复杂的逻辑
                    System.out.println("需要更新的Role："+roleFromDingTalk.toString());
                    roleService.updateRole(roleFromDingTalk); // 假设更新操作是由roleService提供的
                }
                // 从映射中移除，这样最后剩下的就是需要删除的角色
                roleDbMap.remove(roleFromDingTalk.getRoleId());
            } else {
                // 如果角色在数据库中没找到，则插入这个新角色：
                System.out.println("需要新增的Role："+roleFromDingTalk.toString());
                roleService.insertRole(roleFromDingTalk); // 假设插入操作是由roleService提供的
            }
        }

        // 任何在映射中剩下的角色都不存在于钉钉角色列表中，视为被删除
        for (String roleId : roleDbMap.keySet()) {
            // 再次假设，删除由roleService进行
            System.out.println("需要刪除的Role："+roleId);
            roleService.deleteRoleById(roleId);
        }
    }

    public void syncDepartments(List<Department> departments) {
        List<Department> departmentsDb = departmentService.selectAllDepartments();
        // 将数据库中的部门转换为Map，便于快速按部门ID查找
        Map<String, Department> departmentDbMap = departmentsDb.stream()
                .collect(Collectors.toMap(Department::getDepartmentId, department -> department));

        // 同步钉钉的部门信息到数据库
        for (Department deptFromDingTalk : departments) {
            Department deptFromDb = departmentDbMap.get(deptFromDingTalk.getDepartmentId());

            if (deptFromDb == null) {
                // 如果数据库中不存在这个部门，那么添加它
                System.out.println("需要新增的Department："+deptFromDingTalk.toString());
                departmentService.insertDepartment(deptFromDingTalk);
            } else {
                // 如果存在，检查它是否需要更新
                if (!deptFromDb.equals(deptFromDingTalk)) {
                    System.out.println("需要更新的Department："+deptFromDingTalk.toString());
                    departmentService.updateDepartment(deptFromDingTalk);
                }
                // 移除已处理的部门，便于后续处理数据库中多余的部门
                departmentDbMap.remove(deptFromDingTalk.getDepartmentId());
            }
        }

        // 如果Map中还有其他的部门，那么这些部门在钉钉中不存在，应该被删除
        for (String deptId : departmentDbMap.keySet()) {
            System.out.println("需要刪除的Department："+deptId);
            departmentService.deleteDepartmentById(deptId);
        }
    }

    public void syncUserDepartments(List<UserDepartment> userDepartments) {
        List<UserDepartment> userDepartmentsFromDb = userDepartmentService.selectUserDepartmentAll();
        // 先删除数据库中不再存在的关系
        for (UserDepartment userDeptDB : userDepartmentsFromDb) {
            if (!userDepartments.contains(userDeptDB)) {
                // 钉钉列表中不包含该用户-部门关系，需要从数据库删除
                System.out.println("需要刪除的userDept："+userDeptDB.toString());
                userDepartmentService.deleteUserDepartment(userDeptDB.getUserId(), userDeptDB.getDepartmentId());
            }
        }

        // 然后更新已存在的或新增的关系
        for (UserDepartment userDeptDingTalk : userDepartments) {
            if (!userDepartmentsFromDb.contains(userDeptDingTalk)) {
                System.out.println("需要新增的userDept："+userDeptDingTalk.toString());
                userDepartmentService.insertUserDepartment(userDeptDingTalk);
            }
        }
    }

    public void syncUserRoles(List<UserRole> userRoles) {
        List<UserRole> userRoleFromDb = userRoleService.selectUserRoleAll();
        for (UserRole userDeptDB : userRoleFromDb) {
            if (!userRoles.contains(userDeptDB)) {
                System.out.println("需要刪除的UserRole："+userDeptDB.toString());
                userRoleService.deleteUserRole(userDeptDB.getUserId(), userDeptDB.getRoleId());
            }
        }

        // 然后新增的关系
        for (UserRole userRoleDingTalk : userRoles) {
            if (!userRoleFromDb.contains(userRoleDingTalk)) {
                System.out.println("需要新增的UserRole："+userRoleDingTalk.toString());
                userRoleService.insertUserRole(userRoleDingTalk);

            }
        }
    }
}
