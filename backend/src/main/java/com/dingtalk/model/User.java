package com.dingtalk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String userId;
    private String username;
    private String email;
    private String mobile;
    private String avatar;
    private String status;
}
