package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.courses.CanNotCreateException;
import nextstep.courses.CanNotJoinException;
import nextstep.courses.enumerate.ProvideType;
import org.junit.jupiter.api.Test;

class ProvideTest {
    
    @Test
    void 무료강의인데_수강료가있으면_에러전파() throws CanNotCreateException {
        assertThatThrownBy(() -> {
            new Provide(ProvideType.FREE, new ProvidePolicy(10, 10));
        }).isInstanceOf(CanNotCreateException.class)
            .hasMessage("강의타입과 정책이 일치하지 않습니다");
    }
    
    @Test
    void 유료강의에_수강신청한다() throws Exception {
        Provide provide = new Provide(ProvideType.PAID, new ProvidePolicy(10, 5));
        assertThat(provide.applyPaid(8, 5))
            .isTrue();
    }
    
    @Test
    void 무료강의에_지불_후_수강신청한다() throws Exception {
        Provide provide = new Provide(ProvideType.FREE, new ProvidePolicy());
        assertThatThrownBy(() -> {
            assertThat(provide.applyPaid(8, 5));
        }).isInstanceOf(CanNotJoinException.class)
            .hasMessage("무료 강의는 결제할 수 없다");
    }
    
    @Test
    void 무료강의에_지불_없이_수강신청한다() throws Exception {
        Provide provide = new Provide(ProvideType.FREE, new ProvidePolicy());
        assertThatThrownBy(() -> {
            assertThat(provide.applyFree());
        }).isInstanceOf(CanNotJoinException.class)
            .hasMessage("무료강의는 결제할 수 없다");
    }

}