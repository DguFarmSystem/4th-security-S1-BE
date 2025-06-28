package com.farmsystem.sprout.dto.response;

import lombok.Data;

@Data
public class UserInfoApiResponse {
    private int code;
    private int status;
    private String message;
    private UserInfo data;
    private boolean success;

    @Data
    public static class UserInfo {
        private String id;
        private String pw;
        private String name;
        private String gender;
        private String phoneNumber;
        private String pos;
        private String status;
        private String company;
        private String dept;
        private String major;
        private String app;
    }
} 