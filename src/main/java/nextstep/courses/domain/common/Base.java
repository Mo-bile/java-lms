package nextstep.courses.domain.common;

import java.time.LocalDateTime;

public abstract class Base {
    
    private final LocalDateTime createdDate;
    private final LocalDateTime updatedDate;
    
    public Base() {
        this(LocalDateTime.now(), null);
    }
    
    public Base(LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
    
    protected LocalDateTime getCreatedDate() {
        return createdDate;
    }
    
    protected LocalDateTime getUpdatedDate() {
        return updatedDate;
    }
}