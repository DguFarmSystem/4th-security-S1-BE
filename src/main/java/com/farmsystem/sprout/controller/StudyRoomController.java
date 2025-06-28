package com.farmsystem.sprout.controller;

import com.farmsystem.sprout.dto.response.StudyRoomApiResponse;
import com.farmsystem.sprout.dto.response.UserInfoApiResponse;
import com.farmsystem.sprout.dto.response.LibrarySeatApiResponse;
import com.farmsystem.sprout.service.StudyRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/library")
@RequiredArgsConstructor
@Slf4j
public class StudyRoomController {
    
    private final StudyRoomService studyRoomService;
    
    /**
     * 모든 스터디룸 정보를 조회합니다.
     */
    @GetMapping("/studyrooms")
    public Mono<ResponseEntity<StudyRoomApiResponse>> getStudyRooms() {
        return studyRoomService.getStudyRoomsFromLibrary()
                .map(ResponseEntity::ok)
                .doOnError(error -> log.error("스터디룸 조회 중 오류 발생: {}", error.getMessage()))
                .onErrorReturn(ResponseEntity.internalServerError().build());
    }
    
    /**
     * 특정 스터디룸의 상세 정보를 조회합니다.
     */
    @GetMapping("/studyrooms/{roomId}")
    public Mono<ResponseEntity<StudyRoomApiResponse.StudyRoomData>> getStudyRoomDetail(
            @PathVariable String roomId) {
        return studyRoomService.getStudyRoomDetail(roomId)
                .map(ResponseEntity::ok)
                .doOnError(error -> log.error("스터디룸 상세 조회 중 오류 발생: {}", error.getMessage()))
                .onErrorReturn(ResponseEntity.notFound().build());
    }
    
    /**
     * 스터디룸을 예약합니다.
     */
    @PostMapping("/studyrooms/{roomId}/reserve")
    public Mono<ResponseEntity<StudyRoomApiResponse>> reserveStudyRoom(
            @PathVariable String roomId,
            @RequestParam String seatId,
            @RequestParam String userId) {
        return studyRoomService.reserveStudyRoom(roomId, seatId, userId)
                .map(ResponseEntity::ok)
                .doOnError(error -> log.error("스터디룸 예약 중 오류 발생: {}", error.getMessage()))
                .onErrorReturn(ResponseEntity.badRequest().build());
    }
    
    /**
     * 스터디룸 예약을 취소합니다.
     */
    @DeleteMapping("/studyrooms/{roomId}/reserve")
    public Mono<ResponseEntity<StudyRoomApiResponse>> cancelReservation(
            @PathVariable String roomId,
            @RequestParam String seatId,
            @RequestParam String userId) {
        return studyRoomService.cancelReservation(roomId, seatId, userId)
                .map(ResponseEntity::ok)
                .doOnError(error -> log.error("스터디룸 예약 취소 중 오류 발생: {}", error.getMessage()))
                .onErrorReturn(ResponseEntity.badRequest().build());
    }
    
    /**
     * 스터디룸 상태를 확인합니다.
     */
    @GetMapping("/studyrooms/status")
    public Mono<ResponseEntity<String>> getServiceStatus() {
        return Mono.just(ResponseEntity.ok("동국대학교 도서관 스터디룸 API 서비스가 정상적으로 작동 중입니다."));
    }
    
    // 제1열람실 좌석 정보
    @GetMapping("/seats/first")
    public Mono<ResponseEntity<LibrarySeatApiResponse>> getFirstRoomSeats() {
        return studyRoomService.getFirstRoomSeats().map(ResponseEntity::ok)
                .onErrorReturn(ResponseEntity.internalServerError().build());
    }
    
    // 제2열람실 좌석 정보
    @GetMapping("/seats/second")
    public Mono<ResponseEntity<LibrarySeatApiResponse>> getSecondRoomSeats() {
        return studyRoomService.getSecondRoomSeats().map(ResponseEntity::ok)
                .onErrorReturn(ResponseEntity.internalServerError().build());
    }
    
    // 제3열람실 좌석 정보
    @GetMapping("/seats/third")
    public Mono<ResponseEntity<LibrarySeatApiResponse>> getThirdRoomSeats() {
        return studyRoomService.getThirdRoomSeats().map(ResponseEntity::ok)
                .onErrorReturn(ResponseEntity.internalServerError().build());
    }
    
    // 모든 열람실 공통 유저 정보
    @GetMapping("/user/my-info")
    public Mono<ResponseEntity<UserInfoApiResponse>> getUserInfo() {
        return studyRoomService.getUserInfo().map(ResponseEntity::ok)
                .onErrorReturn(ResponseEntity.internalServerError().build());
    }
    
    // 중앙도서관 좌석 정보
    @GetMapping("/seats/central")
    public Mono<ResponseEntity<LibrarySeatApiResponse>> getCentralLibrarySeats() {
        return studyRoomService.getCentralLibrarySeats().map(ResponseEntity::ok)
                .onErrorReturn(ResponseEntity.internalServerError().build());
    }
}
