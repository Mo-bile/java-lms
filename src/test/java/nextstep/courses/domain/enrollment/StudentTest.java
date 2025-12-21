package nextstep.courses.domain.enrollment;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import nextstep.courses.domain.enumerate.ApprovalStatus;
import org.junit.jupiter.api.Test;

class StudentTest {

    @Test
    void 학생의_기본상태는_PENDING_이다() {
        assertThatNoException().isThrownBy(() -> {
            new Student(1L);
        });
    }

    @Test
    void 현재의_수강승인상태에서_같은_상태로_변경하면_에러전파() {
        Student student = new Student(1L);
        assertThatThrownBy(() -> {
            student.changeApprovalStatus(ApprovalStatus.PENDING);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("변경하는 수강상태가 같다");
    }

    @Test
    void 수강_승인_취소_상태에서_PENDING으로_변경할수없다() {
        Student student = new Student(1L, ApprovalStatus.APPROVED);
        assertThatThrownBy(() -> {
            student.changeApprovalStatus(ApprovalStatus.PENDING);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("수강 승인,취소 상태에서는 대기상태로 변경불가하다");
    }

}