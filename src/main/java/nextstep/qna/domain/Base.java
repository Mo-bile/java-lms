package nextstep.qna.domain;

import java.time.LocalDateTime;

public class Base {
    protected LocalDateTime createdDate = LocalDateTime.now();
    protected LocalDateTime updatedDate;
}
