package com.farmsystem.sprout.dto.response;

import lombok.Data;
import java.util.List;

@Data
public class LibrarySeatApiResponse {
    private int code;
    private int status;
    private String message;
    private List<SeatInfo> data;
    private boolean success;

    @Data
    public static class SeatInfo {
        private String seatId;
        private String seatNumber;
        private String status;
        private String userId;
        private String startTime;
        private String endTime;
    }
} 