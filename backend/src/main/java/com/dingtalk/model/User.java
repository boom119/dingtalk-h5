package com.dingtalk.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private String user_id;
    private String username;
    private String email;
    private String mobile;
    private String avatar;
    private Date hired_date;
    private String status;
    private Date create_time;
    private Date update_time;
}
