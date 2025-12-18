package nextstep.courses.infrastructure.entity;

import java.time.LocalDateTime;

public class CourseEntity {
    
    private final Long id;
    private final String title;
    private final Long creatorId;
    private final LocalDateTime createdDate;
    private final LocalDateTime updatedDate;
    
    public CourseEntity(Long id, String title, Long creatorId, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
    
    public Long getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public Long getCreatorId() {
        return creatorId;
    }
    
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
    
    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }
}
