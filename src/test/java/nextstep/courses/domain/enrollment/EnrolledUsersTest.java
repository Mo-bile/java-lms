package nextstep.courses.domain.enrollment;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import nextstep.courses.CanNotJoinException;
import org.junit.jupiter.api.Test;

class EnrolledUsersTest {
    
    @Test
    void 이미등록된_회원이다() {
        EnrolledUsers enrolledUsers = new EnrolledUsers(List.of(1L, 2L, 3L));
        assertThatThrownBy(() -> {
                enrolledUsers.registerUserId(3L);
            }
        ).isInstanceOf(CanNotJoinException.class)
            .hasMessage("이미 수강신청이 완료된 유저입니다");
    }
    
    @Test
    void 등록고객에_수를_추가한다() throws CanNotJoinException {
        EnrolledUsers enrolledUsers = new EnrolledUsers(List.of(1L, 2L, 3L));
        assertThatNoException().isThrownBy(() -> enrolledUsers.registerUserId(4L));
    }
    
}