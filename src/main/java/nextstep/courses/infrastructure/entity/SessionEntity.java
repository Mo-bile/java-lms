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
    private final int coverImageSize; // byte
    private final String coverImageType;
    private final double dimensionsWidth;
    private final double dimensionsHeight;
    private final double dimensionsRatio;
    private final LocalDateTime createdDate;
    private final LocalDateTime updatedDate;
    
    public SessionEntity(Long courseId, Long id, String creatorId, String title, String content, LocalDate startDate, LocalDate endDate, int coverImageSize, String coverImageType, double dimensionsWidth, double dimensionsHeight, double dimensionsRatio, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.courseId = courseId;
        this.id = id;
        this.creatorId = creatorId;
        this.title = title;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.coverImageSize = coverImageSize;
        this.coverImageType = coverImageType;
        this.dimensionsWidth = dimensionsWidth;
        this.dimensionsHeight = dimensionsHeight;
        this.dimensionsRatio = dimensionsRatio;
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
    
    public int getCoverImageSize() {
        return coverImageSize;
    }
    
    public String getCoverImageType() {
        return coverImageType;
    }
    
    public double getDimensionsWidth() {
        return dimensionsWidth;
    }
    
    public double getDimensionsHeight() {
        return dimensionsHeight;
    }
    
    public double getDimensionsRatio() {
        return dimensionsRatio;
    }
    
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
    
    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }
}
