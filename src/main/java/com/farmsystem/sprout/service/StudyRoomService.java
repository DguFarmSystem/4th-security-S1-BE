package com.farmsystem.sprout.service;

import com.farmsystem.sprout.dto.response.StudyRoomApiResponse;
import com.farmsystem.sprout.dto.response.UserInfoApiResponse;
import com.farmsystem.sprout.dto.response.LibrarySeatApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudyRoomService {
    
    private final WebClient libraryWebClient;
    
    /**
     * 동국대학교 도서관 API에서 스터디룸 정보를 가져옵니다.
     */
    public Mono<StudyRoomApiResponse> getStudyRoomsFromLibrary() {
        return libraryWebClient.get()
                .uri("/api/studyrooms") // 실제 API 엔드포인트에 맞게 수정 필요
                .retrieve()
                .bodyToMono(StudyRoomApiResponse.class)
                .doOnSuccess(response -> log.info("도서관 API 호출 성공: {}개의 스터디룸 정보 조회", 
                        response.getData() != null ? response.getData().size() : 0))
                .doOnError(error -> log.error("도서관 API 호출 실패: {}", error.getMessage()));
    }
    
    /**
     * 특정 스터디룸의 상세 정보를 가져옵니다.
     */
    public Mono<StudyRoomApiResponse.StudyRoomData> getStudyRoomDetail(String roomId) {
        return libraryWebClient.get()
                .uri("/api/studyrooms/{roomId}", roomId)
                .retrieve()
                .bodyToMono(StudyRoomApiResponse.class)
                .map(response -> {
                    if (response.getData() != null && !response.getData().isEmpty()) {
                        return response.getData().get(0);
                    }
                    return null;
                })
                .doOnSuccess(data -> log.info("스터디룸 상세 정보 조회 성공: {}", roomId))
                .doOnError(error -> log.error("스터디룸 상세 정보 조회 실패: {}", error.getMessage()));
    }
    
    /**
     * 스터디룸 예약을 시도합니다.
     */
    public Mono<StudyRoomApiResponse> reserveStudyRoom(String roomId, String seatId, String userId) {
        return libraryWebClient.post()
                .uri("/api/studyrooms/{roomId}/reserve", roomId)
                .bodyValue(new ReservationRequest(seatId, userId))
                .retrieve()
                .bodyToMono(StudyRoomApiResponse.class)
                .doOnSuccess(response -> log.info("스터디룸 예약 성공: roomId={}, seatId={}, userId={}", 
                        roomId, seatId, userId))
                .doOnError(error -> log.error("스터디룸 예약 실패: {}", error.getMessage()));
    }
    
    /**
     * 스터디룸 예약을 취소합니다.
     */
    public Mono<StudyRoomApiResponse> cancelReservation(String roomId, String seatId, String userId) {
        return libraryWebClient.delete()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/studyrooms/{roomId}/reserve")
                        .queryParam("seatId", seatId)
                        .queryParam("userId", userId)
                        .build(roomId))
                .retrieve()
                .bodyToMono(StudyRoomApiResponse.class)
                .doOnSuccess(response -> log.info("스터디룸 예약 취소 성공: roomId={}, seatId={}, userId={}", 
                        roomId, seatId, userId))
                .doOnError(error -> log.error("스터디룸 예약 취소 실패: {}", error.getMessage()));
    }
    
    // 제1열람실 좌석 정보
    public Mono<LibrarySeatApiResponse> getFirstRoomSeats() {
        return libraryWebClient.get()
                .uri("/libraries/seats/7")
                .retrieve()
                .bodyToMono(LibrarySeatApiResponse.class);
    }
    // 제2열람실 좌석 정보
    public Mono<LibrarySeatApiResponse> getSecondRoomSeats() {
        return libraryWebClient.get()
                .uri("/libraries/seats/9")
                .retrieve()
                .bodyToMono(LibrarySeatApiResponse.class);
    }
    // 제3열람실 좌석 정보
    public Mono<LibrarySeatApiResponse> getThirdRoomSeats() {
        return libraryWebClient.get()
                .uri("/libraries/seats/10")
                .retrieve()
                .bodyToMono(LibrarySeatApiResponse.class);
    }
    // 모든 열람실 공통 유저 정보
    public Mono<UserInfoApiResponse> getUserInfo() {
        return libraryWebClient.get()
                .uri("/user/my-info")
                .retrieve()
                .bodyToMono(UserInfoApiResponse.class);
    }
    
    // 중앙도서관 좌석 정보
    public Mono<LibrarySeatApiResponse> getCentralLibrarySeats() {
        return libraryWebClient.get()
                .uri("/libraries/seats/1")
                .retrieve()
                .bodyToMono(LibrarySeatApiResponse.class);
    }
    
    // 예약 요청을 위한 내부 클래스
    private static class ReservationRequest {
        private String seatId;
        private String userId;
        
        public ReservationRequest(String seatId, String userId) {
            this.seatId = seatId;
            this.userId = userId;
        }
        
        public String getSeatId() { return seatId; }
        public void setSeatId(String seatId) { this.seatId = seatId; }
        public String getUserId() { return userId; }
        public void setUserId(String userId) { this.userId = userId; }
    }
}
