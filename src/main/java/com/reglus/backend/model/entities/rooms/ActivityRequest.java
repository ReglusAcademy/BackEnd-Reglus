package com.reglus.backend.model.entities.rooms;

import java.time.LocalDateTime;

public class ActivityRequest {
    private Long roomId;
    private Long educatorId;
    private String title;
    private Integer maxPoints;
    private LocalDateTime dataLimit;

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getEducatorId() {
        return educatorId;
    }

    public void setEducatorId(Long educatorId) {
        this.educatorId = educatorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(Integer maxPoints) {
        this.maxPoints = maxPoints;
    }

    public LocalDateTime getDataLimit() {
        return dataLimit;
    }

    public void setDataLimit(LocalDateTime dataLimit) {
        this.dataLimit = dataLimit;
    }
}