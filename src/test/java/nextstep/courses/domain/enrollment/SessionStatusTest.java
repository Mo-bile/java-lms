package nextstep.courses.domain.enrollment;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import nextstep.courses.CanNotJoinException;
import nextstep.courses.domain.enumerate.ProgressStatus;
import org.junit.jupiter.api.Test;

class SessionStatusTest {
    
    @Test
    void 강의_상태가_모집중이_아닌데_신청하면_예외전파() {
        SessionStatus sessionStatus = new SessionStatus(ProgressStatus.PREPARATION);
        assertThatThrownBy(sessionStatus::isApplyStatus)
            .isInstanceOf(CanNotJoinException.class)
            .hasMessage("모집 중 일때만 신청 가능합니다");
    }
    
}