package nextstep.courses.infrastructure.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SessionEntity {
    
    private final Long courseId;
    private final Long id;
    private final String creatorId;
    private final String title;
    private final String content;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final LocalDateTime createdDate;
    private final LocalDateTime updatedDate;

    public SessionEntity(Long courseId, Long id, String creatorId, String title, String content, LocalDate startDate, LocalDate endDate, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.courseId = courseId;
        this.id = id;
        this.creatorId = creatorId;
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
    
    public Long getCourseId() {
        return courseId;
    }
    
    public Long getId() {
        return id;
    }

    public String getCreatorId() {
        return creatorId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getContent() {
        return content;
    }
    
    public LocalDate getStartDate() {
        return startDate;
    }
    
    public LocalDate getEndDate() {
        return endDate;
    }
    
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
    
    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }
}
