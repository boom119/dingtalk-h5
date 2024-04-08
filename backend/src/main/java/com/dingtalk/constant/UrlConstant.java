package com.dingtalk.constant;

/**
 * 钉钉开放接口网关常量
 */
public class UrlConstant {

    /**
     * 获取access_token url
     */
    public static final String GET_ACCESS_TOKEN_URL = "https://oapi.dingtalk.com/gettoken";

    /**
     * 查询用户列表url
     */
    public static final String GET_USER_LIST_URL = "https://oapi.dingtalk.com/topapi/v2/user/list";

    /**
     * 查询部门列表url
     */
    public static final String GET_DEPARTMENT_LIST_URL = "https://oapi.dingtalk.com/topapi/v2/department/listsub";

    /**
     * 查询角色列表url
     */
    public static final String GET_ROLE_LIST_URL = "https://oapi.dingtalk.com/topapi/role/list";

    /**
     * 根据部门id查询部门下所有的用户id列表
     */
    public static final String GET_USERIDS_BY_DEPARTMENTID = "https://oapi.dingtalk.com/topapi/user/listid";

    /**
     * 根据用户userid获取用户详情
     */
    public static final String GET_USERINFO_BY_ID = "https://oapi.dingtalk.com/topapi/v2/user/get";

    /**
     * 获取角色组信息。https://oapi.dingtalk.com/topapi/role/getrolegroup
     *
     */
    public static final String GET_ROLE_GROUP = "https://oapi.dingtalk.com/topapi/role/getrolegroup";
}
