package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Base {
    
    protected LocalDateTime createdDate;
    protected LocalDateTime updatedDate;
    
    public Base() {
        this(LocalDateTime.now(), null);
    }
    
    public Base(LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}