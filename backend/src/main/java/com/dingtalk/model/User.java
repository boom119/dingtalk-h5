package com.dingtalk.model;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class User {

    private String userId;
    private String username;
    private String email;
    private String mobile;
    private String avatar;
    private String status;


}
