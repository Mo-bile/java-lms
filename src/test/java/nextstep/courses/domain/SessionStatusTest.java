package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SessionStatusTest {
    
    @Test
    void 강의_상태가_모집중이_아닌데_신청하면_flase() {
        SessionStatus sessionStatus = new SessionStatus(SessionStatusType.PREPARATION);
        assertThat(sessionStatus.isApplyStatus()).isFalse();
    }
    
}