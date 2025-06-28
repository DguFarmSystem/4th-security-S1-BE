package com.farmsystem.sprout.dto.response;

import lombok.Data;
import java.util.List;

@Data
public class StudyRoomApiResponse {
    private boolean success;
    private String message;
    private List<StudyRoomData> data;
    
    @Data
    public static class StudyRoomData {
        private String roomId;
        private String roomName;
        private String building;
        private String floor;
        private int totalSeats;
        private int availableSeats;
        private int occupiedSeats;
        private String status; // "available", "full", "maintenance"
        private List<SeatInfo> seats;
    }
    
    @Data
    public static class SeatInfo {
        private String seatId;
        private String seatNumber;
        private String status; // "available", "occupied", "reserved", "maintenance"
        private String userId; // 사용 중인 경우
        private String startTime; // 사용 시작 시간
        private String endTime; // 사용 종료 시간
    }
} 